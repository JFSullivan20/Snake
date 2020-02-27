package com.thejfsullivan.snake;

import processing.core.*;

public class SnakeGame extends PApplet {
	
	Snake snake;

    // The argument passed to main must match the class name
    public static void main(String[] args) {
        PApplet.main(new String[] { SnakeGame.class.getName() });
    }

    // method used only for setting the size of the window
    public void settings(){
        size(500, 500);
    }

    public void setup(){
        frameRate(20);
        snake = new Snake(this);
    }

    public void draw(){
    	background(0);
    	snake.move();
    	snake.show();
    }
    
    @Override
    public void keyPressed() {
    	switch (keyCode) {
		case UP:
			snake.changeDirection(0, -1);
			break;
		case DOWN:
			snake.changeDirection(0, 1);
			break;
		case LEFT:
			snake.changeDirection(-1, 0);
			break;
		case RIGHT:
			snake.changeDirection(1, 0);
			break;

		default:
			break;
		}
    }
}