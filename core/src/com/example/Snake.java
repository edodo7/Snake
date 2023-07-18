package com.example;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.ListIterator;

public class Snake {
    private LinkedList<Rectangle> body;
    private Vector2 headDirection;
    private Vector2 tailDirection;
    private int segmentsToAdd;
    private final int WIDTH = 30;
    private final int HEIGHT = 30;
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
        Rectangle head = body.getFirst();
        if (Intersector.overlaps(fruit,head)){
            segmentsToAdd++;
            return true;
        }
        return false;
    }

    public void move(){
        Rectangle currentHead = body.getFirst();
        if (currentHead.x >= Gdx.graphics.getWidth()){
            currentHead.setX(0);
        }
        else if (currentHead.x < 0){
            currentHead.setX(Gdx.graphics.getWidth());
        }
        else if (currentHead.y >= Gdx.graphics.getHeight()){
            currentHead.setY(0);
        }
        else if (currentHead.y < 0){
            currentHead.setY(Gdx.graphics.getHeight());
        }
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

    public boolean hasBittenTail(){
        ListIterator<Rectangle> iterator = body.listIterator(1);
        Rectangle head = body.getFirst();
        while (iterator.hasNext()){
            Rectangle current = iterator.next();
            if (head.overlaps(current)){
                return true;
            }
        }
        return false;
    }
    public LinkedList<Rectangle> getBody() {
        return body;
    }
    private boolean isMovingVertical(){
        return headDirection.y != 0;
    }
    private boolean isMovingHorizontal(){
        return headDirection.x != 0;
    }

}
