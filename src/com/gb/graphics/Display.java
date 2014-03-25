package com.gb.graphics;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Display {
	@FunctionalInterface
	public static interface DisplayInitFunc {
		public Display call(Display d);
	}
	
	private static Display instance;
	private static boolean initialized;
	
	private static int WIDTH = 640;
	private static int HEIGHT = 480;
	
	static {
		initialized = false;
	}
	
	private JFrame frame;
	private Canvas canvas;
	private BufferStrategy buffStrat;
	
	public static Display init() {
		return init((disp) -> { return disp; });
	}
	
	public static Display init(DisplayInitFunc callback) {
		initialized = true;
		
		instance = new Display();
		return instance = callback.call(instance);
	}
	
	private Display() {
		frame = new JFrame();
		canvas = new Canvas();
		
		frame.setSize(640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(canvas);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	private boolean prepareRender() {
		buffStrat = canvas.getBufferStrategy();
		if(buffStrat == null) {
			canvas.createBufferStrategy(3);
			return false;
		}
		
		Graphics2D g = (Graphics2D) buffStrat.getDrawGraphics();
		Draw.setTarget(g);
		
		return true;
	}
	
	private void finishRender() {
		Draw.getTarget().dispose();
		buffStrat.show();
	}
	
	/**
	 * @return pointer to the current display
	 */
	public static Display getIntstance() {
		return instance;
	}
	
	public static boolean isInitialized() {
		return initialized;
	}
	
	public static boolean prepareForRender() {
		return instance.prepareRender();
	}
	
	public static void finalizeRender() {
		instance.finishRender();
	}
	
	public static int getWidth() {
		return WIDTH;
	}
	
	public static int getHeight() {
		return HEIGHT;
	}
	
	public static void setSize(int w, int h) {
		WIDTH = w;
		HEIGHT = h;
		instance.frame.setSize(w, h);
	}
}
