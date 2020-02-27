package com.thejfsullivan.snake;

import processing.core.*;

public class Snake {
	
	PApplet p;

	int x = 0;
	int y = 0;
	int xVel = 0;
	int yVel = 0;
	int headSize = 10;
	
	public Snake(PApplet p) {
		this.p = p;
	}
	
	public Snake(PApplet p, int s) {
		this.p = p;
		headSize = s;
	}

	public void show() {
		p.fill(255);
		p.rect(x, y, 10, 10);
	}

	public void changeDirection(int i, int j) {
		xVel = i;
		yVel = j;
	}

	public void move() {
		x += xVel * headSize;
		y += yVel * headSize;
	}
}
