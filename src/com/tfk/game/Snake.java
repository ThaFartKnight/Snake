package com.tfk.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Snake extends GameObject {
	private Direction direction;
	public Snake(int x, int y, Direction direction) {
		setX(x);
		setY(y);
		this.setBounds(new Rectangle(x, y, width, height));
		this.setDirection(direction);
	}
	public Snake(Snake snake) {
		setX(snake.getX());
		setY(snake.getY());
		updateBounds();
		setDirection(snake.getDirection());
	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		g.fillRect(this.getX(), this.getY(), width, height);
		g.setColor(Color.BLACK);
		g.drawRect(this.getX(), this.getY(), width, height);
	}

	public Direction getDirection() {
		return direction;
	}
	public void updateBounds() {
		setBounds(new Rectangle(this.getX(), this.getY(), width, height));
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
