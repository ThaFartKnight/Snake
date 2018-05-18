package com.tfk.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Apple extends GameObject {
	private Random r = new Random();
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
	public void respawn(Game game) {
		boolean validX = true, validY = true;
		int nextX = r.nextInt(Game.gridWidth - 1), nextY = r.nextInt(Game.gridHeight - 1);
		do {
			for(int i = 0; i < game.snake.size(); i++) {
				if(nextX == game.snake.get(i).getX()) {
					validX = false;
					nextX = r.nextInt(game.gridWidth - 1);
				}
				if(nextY == game.snake.get(i).getY()) {
					validY = false;
					nextY = r.nextInt(Game.gridHeight - 1);
				}
			}
		}while(!validX || !validY);
		this.setX(nextX);
		this.setY(nextY);
	}
}
