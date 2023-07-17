package com.example;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

public class Snake {
    private LinkedList<Rectangle> body;
    private Vector2 headDirection;
    private Vector2 tailDirection;
    private int segmentsToAdd;
    private final int WIDTH = 20; // x axis
    private final int HEIGHT = 20; // y axis
    public Snake(){
        Rectangle head = new Rectangle(50,50,WIDTH,HEIGHT);
        body = new LinkedList<>();
        body.addFirst(head);
        segmentsToAdd = 0;
        headDirection = new Vector2(WIDTH,0);
        tailDirection = new Vector2(headDirection.x,headDirection.y);
    }

    private void grow(){
        Rectangle currentTail = body.getLast();
        Rectangle newTail = new Rectangle(currentTail.x - tailDirection.x,currentTail.y - tailDirection.y, WIDTH,HEIGHT);
        body.addLast(newTail);
    }

    public boolean hasEaten(Circle fruit){
      /**  Rectangle head = body.getFirst();
        if (Intersector.overlaps(fruit,head)){
            segmentsToAdd++;
            return true;
        }
        return false; **/
      segmentsToAdd++;
      System.out.println(segmentsToAdd);
      return true;
    }

    public void move(){
        Rectangle currentHead = body.getFirst();
        Rectangle newHead = new Rectangle(currentHead.x + headDirection.x, currentHead.y + headDirection.y,WIDTH,HEIGHT);
        body.addFirst(newHead);
        Rectangle removedLast = body.removeLast();
        Rectangle currentLast = body.getLast();
        tailDirection.set(currentLast.x - removedLast.x,currentLast.y - removedLast.y);
        if (segmentsToAdd > 0){
            grow();
            segmentsToAdd--;
        }
    }

    public void moveUp(){
        if (headDirection.y >= 0)
            headDirection.set(0,HEIGHT);
    }

    public void moveDown(){
        if (headDirection.y <= 0)
            headDirection.set(0,-HEIGHT);
    }

    public void moveRight(){
        if (headDirection.x >= 0)
            headDirection.set(WIDTH,0);
    }

    public void moveLeft(){
        if (headDirection.x <= 0)
            headDirection.set(-WIDTH,0);
    }

    public LinkedList<Rectangle> getBody() {
        return body;
    }
}
