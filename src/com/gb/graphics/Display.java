package com.gb.graphics;

import java.awt.Canvas;

import javax.swing.JFrame;

public class Display {
	@FunctionalInterface
	public static interface DisplayInitFunc {
		public Display call(Display d);
	}
	
	private static Display instance;
	private static boolean initialized;
	
	static {
		initialized = false;
	}
	
	private JFrame frame;
	private Canvas canvas;
	
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
		
		frame.add(canvas);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public Canvas getCanvas() {
		return canvas;
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
}
