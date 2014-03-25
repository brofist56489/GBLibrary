package com.gb.util.update;

public class LerpUpdater implements Updater, Runnable {
	private long lt;
	private long now;
	private double delta;
	private double nanoSecondsPerTick;
	
	private boolean constantRender;
	
	private Updatable receiver;
	
	private boolean running;
	
	public LerpUpdater(Updatable rec, int ticksPerSecond) {
		this.receiver = rec;
		nanoSecondsPerTick = ticksPerSecond / 1000000000D;
		
		lt = System.nanoTime();
		now = lt;
		delta = 0;
		
		constantRender = false;
		running = false;
	}
	
	public void start() {
		start(false);
	}
	
	public void start(boolean newThread) {
		if(running) {
			System.err.println("LerpUpdater already started");
			return;
		}
		running = true;
		if(newThread) {
			new Thread(this).start();
		} else run();
	}
	
	public void run() {
		while(this.running) {
			now = System.nanoTime();
			delta += (now - lt) * nanoSecondsPerTick;
			lt = now;
			boolean shouldRender = !constantRender;
			
			while(delta >= 1) {
				receiver.update();
				delta--;
				shouldRender = true;
			}
			
			if(shouldRender) {
				receiver.render();
			}
		}
	}
}
