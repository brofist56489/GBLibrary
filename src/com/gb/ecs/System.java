package com.gb.ecs;

import java.util.ArrayList;
import java.util.List;

public abstract class System {
	protected List<Entity> entities;
	protected Class<? extends Component>[] requiredComponents;
	protected Class<? extends Component>[] bannedComponents;
	
	protected Engine engine;
	protected SystemType type;
	
	private SystemCallFunc callFunc;
	
	protected int priority;
	
	public System(int priority, SystemType type, Engine e) {
		this.priority = priority;
		this.engine = e;
		
		this.type = type;
		
		entities = new ArrayList<Entity>();
		
		if(type == SystemType.UPDATE) {
			callFunc = this::update;
		} else if(type == SystemType.RENDER) {
			callFunc = this::render;
		}
	}
	
	public void setRequiredComponents(Class<? extends Component>[] comps) {
		this.requiredComponents = comps;
	}
	
	public void setBannedComponents(Class<? extends Component>[] comps) {
		this.bannedComponents = comps;
	}
	
	public void tryAddEntity(Entity e) {
		if(meetsCriteria(e)) {
			entities.add(e);
		}
	}
	
	protected boolean meetsCriteria(Entity e) {
		boolean works = true;
		
		if(requiredComponents != null)
			for(Class<? extends Component> comp : requiredComponents) {
				if(!e.has(comp)) works = false;
			}
		
		if(bannedComponents != null)
			for(Class<? extends Component> comp : bannedComponents) {
				if(e.has(comp)) works = false;
			}
		
		return works;
	}
	
	public abstract void update(Engine e);
	public abstract void render(Engine e);
		
	public final int getPriority() {
		return priority;
	}
	
	public final SystemType getType() {
		return type;
	}
	
	public final SystemCallFunc getCallFunc() {
		return callFunc;
	}
	
	public final void setCallFunc(SystemCallFunc func) {
		callFunc = func;
	}
	
	public static int compare(System sys1, System sys2) {
		return sys1.getPriority() - sys2.getPriority();
	}
}
