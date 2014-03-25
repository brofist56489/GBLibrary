package com.gb.ecs;

import java.util.ArrayList;
import java.util.List;

import com.gb.util.update.Updatable;

public final class Engine implements Updatable {
	private List<System> systems;
	private List<System> updateSystems;
	private List<System> renderSystems;
	private List<Entity> entities;
	
	public Engine() {
		systems = new ArrayList<System>();
		updateSystems = new ArrayList<System>();
		renderSystems = new ArrayList<System>();
		entities = new ArrayList<Entity>();
	}
	
	public void addSystem(System sys) {
		systems.add(sys);
		
		if(sys.getType() == SystemType.UPDATE) {
			updateSystems.add(sys);
		} else if(sys.getType() == SystemType.RENDER) {
			renderSystems.add(sys);
		}
		
		for(Entity e : entities) {
			sys.tryAddEntity(e);
		}

		systems.sort(System::compare);
		systems.sort(System::compare);
		systems.sort(System::compare);
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void update() {
		for(int i=0, j=updateSystems.size(); i < j; i++) {
			updateSystems.get(i).getCallFunc().act(this);
		}
	}
	
	public void render() {
		for(int i=0, j=renderSystems.size(); i < j; i++) {
			renderSystems.get(i).getCallFunc().act(this);
		}
	}
	
	public List<System> getSystems() {
		return systems;
	}
	
	public List<Entity> getEntity() {
		return entities;
	}
}
