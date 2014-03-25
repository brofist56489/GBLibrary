package com.gb.tests;

import javax.swing.JFrame;

import com.gb.ecs.Engine;
import com.gb.graphics.Display;
import com.gb.util.update.LerpUpdater;

public class ECSTest {
//	private static class TestSystem extends UpdateSystem {
//
//		@SuppressWarnings("unchecked")
//		public TestSystem(int priority, Engine e) {
//			super(priority, e);
//			
//			setRequiredComponents(new Class[] {
//					TestComponent2.class,
//			});
//			
//			setBannedComponents(new Class[] {
//					TestComponent.class,
//			});
//		}
//
//		public void update(Engine e) {
//			System.out.println("System 1");
////			for(Entity ent : entities) {
////				System.out.println(ent.get(TestComponent.class).test);
////			}
//		}
//	}
//	
//	private static class TestSystem2 extends UpdateSystem {
//		
//		public TestSystem2(int priority, Engine e) {
//			super(priority, e);
//		}
//		
//		public void update(Engine e) {
//			System.out.println("System 2");
//		}
//	}
//	
//	private static class TestComponent extends Component {
//		
//		public String test;
//		
//		public TestComponent(String t) {
//			test = t;
//		}
//
//		public void initialize() {			
//		}
//	}
//	
//	private static class TestComponent2 extends Component {
//		public String test;
//		public TestComponent2(String t) {
//			test = t;
//		}
//		
//		public void initialize() {
//		}
//	}
	
	public static void main(String[] args) {		
		Engine eng = new Engine();
		
		Display.init((display) -> {
			JFrame frame = display.getFrame();
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			return display;
		});
		
		LerpUpdater upd = new LerpUpdater(eng, 1);
		upd.start();
	}
}
