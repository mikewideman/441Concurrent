package stub;

import java.util.Vector;

import game.*;

public class StubBoard extends Board {
	private Vector<Entity> entities;
	public StubBoard() {
		super();
		entities = new Vector<Entity>();
		entities.add(new StubCentipede(this));
		entities.add(new StubCentipede(this));
		entities.add(new StubCentipede(this));
		
	}
	public Iterable<Entity> getAllEntities() {
		// TODO Auto-generated method stub
		return entities;
	}
	public int getHeight() {
		// TODO Auto-generated method stub
		return 500;
	}
	public int getWidth() {
		// TODO Auto-generated method stub
		return 500;
	}
	public void addPlayer(Player p){
		entities.add(p);
	}
	
}
