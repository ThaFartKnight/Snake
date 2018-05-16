package com.tfk.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Apple extends GameObject {
	
	public Apple(int x, int y) {
		this.setX(x);
		this.setY(y);
		this.setBounds(new Rectangle(x, y, width, height));
	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		g.fillRect(getX(), getY(), width, height);
	}

}
