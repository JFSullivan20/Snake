package com.thejfsullivan.snake;

import java.util.ArrayList;

import processing.core.*;

public class Snake {
	
	PApplet p;
	
	BodyPart head;
	ArrayList<BodyPart> tail;

	int xVel = 1;
	int yVel = 0;
	int size;
	int growthConstant;
	int length; // length of tail
	boolean isAlive = true;
	
	public Snake(PApplet p) {
		this(p, 10);
	}
	
	public Snake(PApplet p, int s) {
		this(p, s, 1);
	}
	
	public Snake(PApplet p, int s, int g) {
		this.p = p;
		this.size = s;
		head = new BodyPart(0, 0);
		tail = new ArrayList<>();
		growthConstant = g;
	}

	public void show() {
		p.fill(255);
		for (BodyPart b : tail) {
			p.rect(b.x, b.y, size, size);
		}
		p.fill(0,255,0);
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
			
			head.x = PApplet.constrain(head.x, 0, p.width - size);
			head.y = PApplet.constrain(head.y, 0, p.height - size);
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
			length += growthConstant; // grow the snake
			return true;
		}
		return false;
	}
	
	public boolean death() {
		// TODO: wall death
		
		// returns true if head is colliding with the tail
		return tail.contains(head);
	}
	
	private class BodyPart extends Cell {
		private BodyPart(int x, int y) {
			super(x, y);
		}
	}
}
