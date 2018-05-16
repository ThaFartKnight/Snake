package com.tfk.game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	private int x, y;
	public static int width = 20, height = 20;
	private Rectangle bounds;
	
	public abstract void render(Graphics g);
	
	public Rectangle getBounds() {
		return bounds;
	}
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
