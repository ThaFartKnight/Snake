package com.tfk.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter {
	Game game;
	public Input(Game game) {
		this.game = game;
	}
	public void keyPressed(KeyEvent e) {
		if(game.getAlive()) {
			Direction d = game.getDirection();
			if(e.getKeyCode() == KeyEvent.VK_UP  && d != Direction.DOWN) {
				game.setDirection(Direction.UP);
			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT && d != Direction.LEFT) {
				game.setDirection(Direction.RIGHT);
			}else if(e.getKeyCode() == KeyEvent.VK_DOWN && d != Direction.UP) {
				game.setDirection(Direction.DOWN);
			}else if(e.getKeyCode() == KeyEvent.VK_LEFT && d != Direction.RIGHT) {
				game.setDirection(Direction.LEFT);
			}
		}
	}
}
