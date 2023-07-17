package com.example;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Main extends ApplicationAdapter {

	private ShapeRenderer shape;
	private Snake snake;
	private Circle fruit;
	private int clock;

	@Override
	public void create() {
		shape = new ShapeRenderer();
		snake = new Snake();
		fruit = new Circle();
		clock = 0;
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(Color.GREEN);
		for (Rectangle segment : snake.getBody()){
			shape.rect(segment.x, segment.y, segment.width, segment.height);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			snake.moveUp();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			snake.moveDown();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			snake.moveLeft();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			snake.moveRight();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			snake.hasEaten(fruit);
		}
		if (clock % 15 == 0){
			snake.move();
		}
		clock++;
		shape.end();
	}
}
