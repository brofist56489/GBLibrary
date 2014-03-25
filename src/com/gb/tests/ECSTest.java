package com.gb.tests;

import java.awt.Color;

import javax.swing.JFrame;

import com.gb.ecs.Engine;
import com.gb.ecs.RenderSystem;
import com.gb.graphics.Display;
import com.gb.graphics.Draw;
import com.gb.util.update.LerpUpdater;

public class ECSTest {

	private static class TestSystem2 extends RenderSystem {
		
		public TestSystem2(int priority, Engine e) {
			super(priority, e);
		}
		
		public void render(Engine e) {
			if(!Display.prepareForRender()) return;
			Draw.clear(Color.black);
			
			Draw.drawRect((int) ((System.currentTimeMillis()/10) % 100), (int) ((System.currentTimeMillis()/10) % 100), 100, 100, Color.red);
			
			Display.finalizeRender();
		}
	}

	
	public static void main(String[] args) {		
		Engine eng = new Engine();
		eng.addSystem(new TestSystem2(0, eng));
		
		Display.init((display) -> {
			JFrame frame = display.getFrame();
			
			frame.setVisible(true);
			return display;
		});
		
		LerpUpdater upd = new LerpUpdater(eng, 30);
		upd.setConstantRender(true);
		upd.start();
	}
}
