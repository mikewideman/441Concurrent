package game;

import java.awt.Point;
import gui.EntitySprite;
import gui.MushroomSprite;

public class Mushroom implements Entity {

	private Board m_board;
	private int m_curHealth;
	private Point m_location;
	private EntitySprite m_sprite;
	private Rectangle m_boundingBox;
	private final EntityTypes m_type;

	// Maximum and starting health of the mushroom.
	private final int MAX_HEALTH = 5;

	// Length of one side of the Mushroom's bounding box (in pixels)
	private final int SQUARE_SIZE = Board.TILE_SIZE;
	
	/**
	 * Safely create a new mushroom and set up its sprite so
	 * as not to let the mushroom escape during construction.
	 * @param board the game board
	 * @param location the point which the mushroom should occupy
	 * @return a new Mushroom
	 */
	public static Mushroom createMushroom(Board board, Point location) {
		Mushroom m = new Mushroom(board, location);
		m.m_sprite = new MushroomSprite(m);
		return m;
	}
	
	private Mushroom(Board board, Point location) {
		m_board = board;
		m_curHealth = MAX_HEALTH;
		m_location = location;
		m_type = EntityTypes.MUSHROOM;
		recalcBoundingBox();
	}

	/**
	 * Recalculates the bounding box for the mushroom based on current location.
	 */
	private void recalcBoundingBox() {
		int[] p = getLocation();
		m_boundingBox = Rectangle.fromCenter(p[0], p[1], SQUARE_SIZE,
				SQUARE_SIZE);
	}

	/**
	 * Mushrooms get hurt if they are hit by a bullet. They ignore other
	 * collisions.
	 * 
	 * @param entityType the type of entity this collided with.
	 */
	public void collidesWith(EntityTypes entityType) {
		if (entityType == EntityTypes.BULLET) {
			die();
		}
	}

	/**
	 * Decrement health. If health is below 0, remove ourselves from the board.
	 */
	public void die() {
		m_curHealth--;
		if (m_curHealth <= 0) {
			m_board.removeEntity(this);
		}
	}

	/**
	 * Return a sprite representation of the entity.
	 * 
	 * @return this mushroom's EntitySprite
	 */
	public EntitySprite getSprite() {
		return m_sprite;
	}

	/**
	 * Return our current location.
	 * 
	 * @return an int array containing the mushroom's x,y coords.
	 */
	public int[] getLocation() {
		// don't hand out the actual Point object due to concurrency issues
		return new int[] { m_location.x, m_location.y };
	}

	/**
	 * Return our bounding box. We can hand out the rectangle directly because
	 * it's immutable.
	 * 
	 * @return this mushroom's bounding Game.Rectangle
	 */
	public Rectangle getBoundingBox() {
		return m_boundingBox;
	}

	/**
	 * This run loop is unnecessary, but included for consistency with other
	 * entities.
	 */
	public void run() {
		while (m_curHealth > 0) {
			Thread.yield();
		}
	}

	/**
	 * Lives left in this mushroom According to the game: 5 = full health 1 =
	 * lowest health (still alive) outside of interval [1, #Life images] will
	 * prevent drawing
	 * 
	 * @return this mushroom's current health
	 */
	public int getHealth() {
		return this.m_curHealth;
	}

	/**
	 * Return the mushroom's entity type.
	 * 
	 * @return EntityTypes.MUSHROOM
	 */
	public EntityTypes getType() {
		return m_type;
	}
	
	/**
	 * Adjust this mushroom's location to a
	 * new pair of coords without invoking a
	 * full move.
	 * 
	 * @param x new x coord to move to
	 * @param y new y coord to move to
	 */
	public void updateLocation(int x, int y) {
		m_location.x = x;
		m_location.y = y;
	}

}
