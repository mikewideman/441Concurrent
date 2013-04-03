package game;
public class Board {
	/**The standard width and height of a grid cell on the board. 
	 * All entities should use this, not their own definition 
	 */
	public static final int SQUARE_SIZE = 50;
	
	private ArrayList<Quad> quads;

	public Board() {
		quads = new ArrayList<Quad>();
	}

	/** get the width of the board in pixels **/
	public int getWidth() {
		return 0;
	}
	/** get the height of the board in pixels **/
	public int getHeight() {
		return 0;
	}
	
	public void move(int x, int y, Entity entity) {
		
		/*
		 * At some point we have to lock on the entity in order to give it an
		 * update as to its current location (and direction). If a collision is
		 * going to occur at this point, we can't have our cake and eat it too:
		 * it won't be possible to getLocation() of the other entity to detect
		 * the collision if it's being locked. Board has to keep track of the
		 * locations to which the Entities are requesting to move, so that it
		 * can detect a collision.
		 */
		
		int size = quads.size();
		if (size) {
			// figure out which one of the quads
			// this Entity wants to move inside of.
			for (int i = 0; i < size; i++) {
				if quads[i].containsPoint(x, y) {
					if (quads[i].isOccupied()) {
						// split quads and redistribute stuff
					} else {
						// add the Entity to the quad
						quads[i].addOccupant(entity);
					}
					return;
				}
			}
		} else {
			// no quads, go ahead and create
			// one that encompasses the entire board.
			Quad quad = new Quad(0, 0, this.width, this.height, entity);
			this.quads.add(quad);
		}
	}

	//while iterating over the entities, will the state be consistent?
	//, or could they have move while drawing them??
	public Iterable<Entity> getAllEntities() {
		return null;
	}

	class Quad {

		private int x;
		private int y;
		private int width;
		private int height;
		private ArrayList<Entity> occupants;

		private Quad(int x, int y, int w, int h, Entity occupant) {
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
			this.occupants = new ArrayList<Entity>();
			this.occupants.add(occupant);
		}

		public boolean containsPoint(int x, int y) {
			return ((x >= this.x) && (x <= (this.x + this.width))
				&& (y >= this.y) && (y <= (this.y + this.height)));
		}

		public boolean isOccupied() {
			return (occupants.size() > 0);
		}

		public void addOccupant(Entity e) {
			this.occupants.add(e);
			// deal with collisions
		}

		public static Quad makeQuad(int x, int y, int w, int h, Entity occupant) {
			Quad newQuad = new Quad(x, y, w, h, occupant);
			return newQuad;
		}

	}
}
