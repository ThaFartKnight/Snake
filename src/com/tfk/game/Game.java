package com.tfk.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	private Direction newDirection = Direction.RIGHT;
	
	private static Thread thread;
	protected LinkedList<Snake> snake = new LinkedList<Snake>();
	protected Apple apple;
	
	private int score = 0;
	public static int gridWidth = Window.width / GameObject.width;

	public static int gridHeight = Window.height / GameObject.height;
	public Random r = new Random();
	
	private boolean alive = true;
	private static Boolean running = false;
	private boolean ate = false;
	
	
	public Game() {
		new Window("snek", this);
		addKeyListener(new Input(this));
		snake.add(new Snake(0,0, Direction.RIGHT));
		apple = new Apple(300, 300);
		Start();
	}
	public synchronized void Start() {
		thread = new Thread();
		running = true;
		run();
	}
	public static synchronized void Stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			requestFocus();
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Window.width, Window.height);
		if(!alive) {
			dead();
		}
		snake.forEach(s ->{
			s.render(g);
		});
		apple.render(g);
		g.setFont(new Font("Times New Roman", Font.BOLD, 25));
		g.setColor(Color.BLACK);
		g.drawString("Score: " + score, 20, 20);
		g.dispose();
		bs.show();
	}
	private void dead() {
		// TODO Auto-generated method stub
		score = 0;
		snake = new LinkedList<Snake>();
		newDirection = Direction.RIGHT;
		snake.add(new Snake(0, 0, Direction.RIGHT));
		apple = new Apple(r.nextInt(gridWidth - 1)*GameObject.width, r.nextInt(gridHeight - 1)*GameObject.height);
		alive = true;
	}
	private void tick() {
		if(alive) {
			move(ate);
			outOfWindow();
			collision();
		}
	}
	private void outOfWindow() {
		// TODO Auto-generated method stub
		if(!snake.getFirst().getBounds().intersects(Window.bounds)) {
			alive = false;
			dead();
		}
	}
	public void run() {
		long lastTime = System.nanoTime();
	    double amountofTicks = 4.0;
	    double ns = 1000000000 / amountofTicks;
	    double delta = 0;
	    long timer = System.currentTimeMillis();
	    while(running) {
	    	long now = System.nanoTime();
	        delta += (now - lastTime) / ns;
	        lastTime = now;
	        while(delta >= 1) {
	        	tick();
	            delta--;
	        }
	        if(running)
	        	render();
	            if(System.currentTimeMillis() - timer > 1000) {
	            	timer += 1000;
	            }
	     }
	}
	
	private void move(boolean extend) {
		LinkedList<Snake> newList = new LinkedList<Snake>();
		for(int i = 0; i < snake.size(); i++) {
			if(i == 0) {
				snake.getFirst().setDirection(newDirection);
				int x = 0, y = 0;
				switch(snake.get(i).getDirection()) {
				case UP:
					x = snake.get(i).getX();
					y = snake.get(i).getY() - GameObject.height;
					break;
				case DOWN:
					x = snake.get(i).getX();
					y = snake.get(i).getY() + GameObject.height;
					break;
				case LEFT:
					x = snake.get(i).getX() - GameObject.width;
					y = snake.get(i).getY();
					break;
				case RIGHT:
					x = snake.get(i).getX() + GameObject.width;
					y = snake.get(i).getY();
					break;
				}
				Snake s = new Snake(snake.getFirst());
				s.setX(x);
				s.setY(y);
				s.updateBounds();
				newList.add(s);
			}else {
				newList.add(new Snake(snake.get(i - 1)));
			}
			if(ate && i + 1 == snake.size()) {
				newList.add(new Snake(snake.getLast()));
				ate = false;
			}
		}
		snake = newList;
	}
	private void collision() {
		// TODO Auto-generated method stub
		if(snake.get(0).getBounds().intersects(apple.getBounds())) {
			score++;
			apple.respawn(this);
			ate = true;
		}
		if(snake.size() > 2) {
			for(int i = 2; i < snake.size(); i++ ) {
				if(snake.getFirst().getBounds().intersects(snake.get(i).getBounds())) {
					alive = false;
				}
			}
		}
	}
	public boolean getAlive() {
		return alive;
	}
	public Direction getDirection() {
		return newDirection;
	}
	public void setDirection(Direction d) {
		this.newDirection = d;
	}
}
