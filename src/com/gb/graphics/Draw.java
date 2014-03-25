package com.gb.graphics;

import java.awt.Color;
import java.awt.Graphics2D;

public final class Draw {
	public static enum DisplayType {
		NORMAL, PER_PIXEL;
	}
	
	private static DisplayType renderType;
	private static Graphics2D target;
	
	public static void clear(Color color) {
		target.setColor(color);
		target.fillRect(0, 0, Display.getWidth(), Display.getHeight());
	}
	
	public static void drawRect(int x, int y, int w, int h, Color color) {
		target.setColor(color);
		target.fillRect(x, y, w, h);
	}
	
	public static DisplayType getRenderType() {
		return renderType;
	}
	
	public static Graphics2D getTarget() {
		return target;
	}
	
	public static void setRenderType(DisplayType type) {
		renderType = type;
	}
	
	public static void setTarget(Graphics2D target) {
		Draw.target = target;
	}
}
