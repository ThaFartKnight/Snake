package com.tfk.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = 6980614585263434684L;
	public static int width = 600, height = 600;
	public static Rectangle bounds = new Rectangle(0,0,width,height);
	public Window(String title, Game game) {
		JFrame frame = new JFrame(title);
		frame.setSize(new Dimension(width, height + 35));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.add(game);
	}
}
