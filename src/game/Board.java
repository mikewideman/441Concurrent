package game;

import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * IN CASE OF EMERGENCY
 * This Board processes move requests sequentially. It's not concurrent, but it should work in a pinch.
 * @author julianboilen
 *
 */
public class Board {

	Vector<Entity> allEntities;
	BlockingQueue<MoveRequest> requests;
	private final int QUEUE_SIZE = Integer.MAX_VALUE;

	public Board() {
		allEntities = new Vector<Entity>();
		requests = new LinkedBlockingQueue<MoveRequest>(QUEUE_SIZE);
		
		(new Thread(new Runnable(){

			public void run() { processRequests();}})).start();//run the process loop in another thread
	}

	// the meat of the heart
	public void move(int x, int y, Entity entity) {
		MoveRequest req = new MoveRequest(entity, x, y);
		requests.offer(req);
	}

	// process requests (run in another thread)
	private void processRequests() {
		Mushroom theWall = new Mushroom(this, new java.awt.Point(0, 0));
		while (true) {
			try {
				MoveRequest req = requests.take();// blocks if no elements
				Entity entity = req.getEntity();
				Rectangle oldLoc = entity.getBoundingBox();
				Rectangle newLoc = Rectangle.fromCenter(req.getX(), req.getY(),
						oldLoc.getWidth(), oldLoc.getHeight());
				if (newLoc.getX() < 0 || newLoc.getX() > getWidth()
						|| newLoc.getY() < 0 || newLoc.getY() > getHeight()) {// collided
					// with
					// the
					// wall
					entity.collidesWith(theWall);
				} else {// not the wall? let's see what else you could run in to
					boolean collidedWithAnotherEntity = false;
					for (Entity e : allEntities) {// check the other entities
						if (e != entity && newLoc.contains(e.getBoundingBox())) {
							collidedWithAnotherEntity = true;
							e.collidesWith(entity);
							entity.collidesWith(e);
							// now go talk it out, you two
						}// end each entity
					}
					if (!collidedWithAnotherEntity) {
						// no collisions
						entity.updateLocation(newLoc.getX(), newLoc.getY());
					}// end no collisions
				}// end not wall
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/** get the width of the board in pixels **/
	public int getWidth() {
		return 500;
	}

	/** get the height of the board in pixels **/
	public int getHeight() {
		return 500;
	}

	public Entity createEntity(int x, int y, EntityTypes type) {
		//TODO: DO it;
		switch (type){
		}
		return null;
	}

//consistent state as long as entity updates location atomically
	public Iterable<Entity> getAllEntities() {
		return Collections.unmodifiableList(allEntities);
	}

	class MoveRequest {
		private Entity entity;
		private int x, y;

		public MoveRequest(Entity entity, int x, int y) {
			super();
			this.entity = entity;
			this.x = x;
			this.y = y;
		}

		public Entity getEntity() {
			return entity;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

	}

}
