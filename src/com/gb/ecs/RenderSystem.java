package com.gb.ecs;

public abstract class RenderSystem extends System {

	public RenderSystem(int priority, SystemType type, Engine e) {
		super(priority, type, e);
	}

	public final void update(Engine e) {
	}
}
