package com.gb.ecs;

public abstract class UpdateSystem extends System {

	public UpdateSystem(int priority, Engine e) {
		super(priority, SystemType.UPDATE, e);
	}
	
	public final void render(Engine e) {
	}
}
