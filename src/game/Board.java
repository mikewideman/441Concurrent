package game;
public class Board {
	
	/** get the width of the board in pixels **/
	public int getWidth(){return 0;}
	/** get the height of the board in pixels **/
	public int getHeight(){return 0;}
	
	//while iterating over the entities, will the state be consistent?
	//, or could they have move while drawing them??
	public Iterable<Entity> getAllEntities() {
		return null;
	}
}
