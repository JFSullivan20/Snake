package com.thejfsullivan.snake;

import java.util.ArrayList;
//import java.util.Random;

import processing.core.*;

public class Snake {
	
	PApplet p;
//	Random rand = new Random();
	
	BodyPart head;
	ArrayList<BodyPart> tail;

	int xVel = 1;
	int yVel = 0;
	int size;
	int growthConstant;
	int length; // length of tail
	boolean isAlive = true;
	
	// score variables
	double score = 0;
	int movesTillFruit = 0;
	double scoreConstant = 100;
	
	public Snake(PApplet p) {
		this(p, 10);
	}
	
	public Snake(PApplet p, int s) {
		this(p, s, 1);
	}
	
	public Snake(PApplet p, int s, int g) {
		this.p = p;
		this.size = s;
		head = new BodyPart(size, size);
		tail = new ArrayList<>();
		growthConstant = g;
		System.out.println(scoreConstant);
	}

	public void show() {
		// for white
		p.fill(255);
		for (BodyPart b : tail) {
			p.rect(b.x, b.y, size, size);
		}
		//for random colors
//		for (int i = 0; i < tail.size(); i++) {
//			p.fill(rand.nextFloat() * 255, rand.nextFloat() * 255, rand.nextFloat() * 255);
//			p.rect(tail.get(i).x, tail.get(i).y, size, size);
//		}
		p.fill(255);
		p.rect(head.x, head.y, size, size);
	}
	
	public void showDeath() {
		p.fill(255,0,0);
		for (BodyPart b : tail) {
			p.rect(b.x, b.y, size, size);
		}
		p.rect(head.x, head.y, size, size);
	}

	public void changeDirection(int i, int j) {
		xVel = i;
		yVel = j;
	}

	public boolean move() {
		if (isAlive) {
			if (length > 0) {
				if (length == tail.size() && !tail.isEmpty()) {
					tail.remove(0);
				}
				tail.add(new BodyPart(head.x, head.y));
			}
			
			head.x += xVel * size;
			head.y += yVel * size;
			
			movesTillFruit++;
			
			head.x = PApplet.constrain(head.x, size, p.width - 2 * size);
			head.y = PApplet.constrain(head.y, size * 2, p.height - 2 * size);
		}
		
		if (death()) {
			isAlive = false;
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean eat(Food f) {
		if (head.equals(f)) {
			score += ( ( length + 1 ) / ( movesTillFruit / scoreConstant ) );
			movesTillFruit = 0;
			length += growthConstant; // grow the snake
			return true;
		}
		return false;
	}
	
	public boolean death() {
		// TODO: wall death ( might already work ? )
		
		// returns true if head is colliding with the tail
		return tail.contains(head);
	}
	
	private class BodyPart extends Cell {
		private BodyPart(int x, int y) {
			super(x, y);
		}
	}

	public void displayScore() {
		p.fill(255);
		p.text("Score: " + Math.round(score), size / 2, size * 3 / 4);
	}
}
