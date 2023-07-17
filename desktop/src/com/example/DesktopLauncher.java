package com.example;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;



public class DesktopLauncher {
	public static final int WIDTH = 900;
	public static final int HEIGHT = 700;

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setIdleFPS(60);
		config.setTitle("Snake");
		config.setWindowedMode(WIDTH,HEIGHT);
		config.setResizable(false);
		config.setWindowIcon("snake.png");
		new Lwjgl3Application(new Main(), config);
	}
}
