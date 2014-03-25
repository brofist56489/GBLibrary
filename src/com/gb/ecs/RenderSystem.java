package com.gb.ecs;

public abstract class RenderSystem extends System {

	public RenderSystem(int priority, Engine e) {
		super(priority, SystemType.RENDER, e);
	}

	public final void update(Engine e) {
	}
}
