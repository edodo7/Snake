package com.example.snake;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Controller {

    private @FXML Canvas canvas;

    @FXML
    public void initialize(){

    }

    @FXML
    public void changeColor(KeyEvent keyEvent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if (keyEvent.getCode() == KeyCode.LEFT){
            gc.setFill(Color.BLUE);
        }
        if (keyEvent.getCode() == KeyCode.RIGHT){
            gc.setFill(Color.YELLOW);
        }
        if (keyEvent.getCode() == KeyCode.UP){
            gc.setFill(Color.RED);
        }
        if (keyEvent.getCode() == KeyCode.DOWN){
            gc.setFill(Color.GREEN);
        }
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
    }

    public Canvas getCanvas() {
        return canvas;
    }
}