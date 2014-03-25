package com.gb.ecs;

public abstract class Component {
	
	protected String name;
	protected Entity entity;
	
	public String getName() {
		return name;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity e) {
		entity = e;
	}
	
	public void initialize() {
	}
}
