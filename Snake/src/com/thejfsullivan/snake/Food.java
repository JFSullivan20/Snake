package com.thejfsullivan.snake;

import java.util.Random;

import processing.core.*;

public class Food extends Cell {
	
	PApplet p;
	Random rand = new Random();
	
	int size;
	
	public Food(PApplet p) {
		this(p, 10);
	}

	public Food(PApplet p, int s) {
		super();
		this.p = p;
		this.size = s;
		randomizeLocation();
	}
	
	public void show() {
		p.fill(255, 0, 0);
		p.rect(x, y, size, size);
	}
	
	public void randomizeLocation() {
		int cols = p.width / size;
		int rows = p.height / size;
		
		x = (int) Math.floor(rand.nextInt(cols)) * size;
		y = (int) Math.floor(rand.nextInt(rows)) * size;
	}

}
