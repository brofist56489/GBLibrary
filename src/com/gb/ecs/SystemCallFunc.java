package com.gb.ecs;

@FunctionalInterface
public interface SystemCallFunc {
	public abstract void act(Engine e);
}
