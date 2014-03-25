package com.gb.ecs;

import java.util.HashMap;

public final class Entity {
	HashMap<Class<? extends Component>, Component> components;
	
	public Entity() {
		components = new HashMap<Class<? extends Component>, Component>();
	}
	
	public <T extends Component> boolean has(Class<T> compClass) {
		return components.containsKey(compClass);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Component> T get(Class<T> compClass) {
		if(!has(compClass)) {
			throw new IllegalArgumentException("No component");
		}
		return (T)components.get(compClass);
	}
	
	public Entity add(Component comp) {
		if(has(comp.getClass())) {
			throw new IllegalArgumentException("Already have component " + comp.getName());
		}
		components.put(comp.getClass(), comp);
		comp.setEntity(this);
		
		comp.initialize();		
		return this;
	}
	
	public Entity remove(Class<? extends Component> compClass) {
		if(has(compClass)) {
			components.remove(compClass);
		}
		return this;
	}
}
