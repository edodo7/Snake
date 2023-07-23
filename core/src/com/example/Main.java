package com.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;
import java.util.Random;

public class Main extends ApplicationAdapter {

	private ShapeRenderer shape;
	private Snake snake;
	private Circle fruit;
	private int clock;
	private Random random;

	@Override
	public void create() {
		shape = new ShapeRenderer();
		snake = new Snake();
		clock = 0;
		random = new Random();
		int x = random.nextInt((Gdx.graphics.getWidth() - 50) - 50 + 1) + 50;
		int y = random.nextInt((Gdx.graphics.getHeight() - 50) - 50 + 1) + 50;
		fruit = new Circle(x,y,10);
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(Color.GREEN);
		for (Rectangle segment : snake.getBody()){
			shape.rect(segment.x, segment.y, segment.width, segment.height);
		}
		shape.setColor(Color.RED);
		shape.circle(fruit.x, fruit.y, fruit.radius);
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			snake.moveUp();
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			snake.moveDown();
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			snake.moveLeft();
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			snake.moveRight();
		}
		if (snake.hasEaten(fruit)) {
			LinkedList<Rectangle> snakeBody = snake.getBody();
			boolean fruitOnSnakeBody;
			do {
				fruitOnSnakeBody = false;
				int x = random.nextInt((Gdx.graphics.getWidth() - 50) - 50 + 1) + 50;
				int y = random.nextInt((Gdx.graphics.getHeight() - 50) - 50 + 1) + 50;
				fruit.setPosition(x, y);
				for (Rectangle segment : snakeBody) {
					if (Intersector.overlaps(fruit, segment)) {
						fruitOnSnakeBody = true;
						break;
					}
				}
			} while (fruitOnSnakeBody);
		}
		if (snake.hasBittenTail()){
			System.exit(0);
		}
		if (clock % 5 == 0){
			snake.move();
		}
		clock++;
		shape.end();
	}
}
