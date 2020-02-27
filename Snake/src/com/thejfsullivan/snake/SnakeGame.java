package com.thejfsullivan.snake;

import processing.core.*;

public class SnakeGame extends PApplet {
	
	Snake snake;
	Food food;
	int size = 20;
	public int growthConstant = 5;

    // The argument passed to main must match the class name
    public static void main(String[] args) {
        PApplet.main(new String[] { SnakeGame.class.getName() });
    }

    // method used only for setting the size of the window
    public void settings(){
        size(500, 500);
    }

    public void setup(){
        frameRate(15);
        snake = new Snake(this, size, growthConstant);
        food = new Food(this, size);
    }

    @SuppressWarnings("unlikely-arg-type")
	public void draw(){
    	background(0);
    	
    	if (snake.eat(food)) {
    		do {
    			food.randomizeLocation();
    		} while (snake.tail.contains(food));
    	}
    	food.show();
    	if (!snake.move()) { // snake dies
    		snake.showDeath();
    	} else {
        	snake.show();
    	}
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
		case 32: // space bar
			setup();
			break;
			
		default:
			break;
		}
    }
}