/**
 * Bullet -- A bullet entity, spawned by a player. Travels towards the top of the board
 * until it either collides with something or leaves the board.
 */
package game;

import gui.BulletSprite;
import gui.EntitySprite;

import java.awt.Point;
import game.Rectangle;

public class Bullet implements Entity, Runnable {

	private Board m_board;
	private Point m_location;
	private EntitySprite m_sprite;
	private Rectangle m_boundingBox;
	private boolean m_active;
	private final EntityTypes m_type;

	// The height/width of a bullet entity, in pixels. Useful for determining
	// bounding box.
	private final int HEIGHT = Board.TILE_SIZE;
	private final int WIDTH = Board.TILE_SIZE / 3;

	// The distance moved by a bullet in each step
	private final int MOVE_DX = 0;
	private final int MOVE_DY = -1;

	/**
	 * Safely create a new bullet and set up its sprite so
	 * as not to let a ref to the bullet escape during construction.
	 * @param board the game board
	 * @param location the point which the bullet should occupy
	 * @return a new Bullet
	 */
	public static Bullet createBullet(Board board, Point location) {
		Bullet b = new Bullet(board, location);
		b.m_sprite = new BulletSprite(b);
		return b;
	}
	
	private Bullet(Board board, Point location) {
		m_board = board;
		m_location = location;
		m_type = EntityTypes.BULLET;
		recalcBoundingBox();
	}

	/**
	 * Make a move on the board.
	 */
	public void move() {
		m_board.move(m_location.x + MOVE_DX, m_location.y + MOVE_DY, this);
	}

	/**
	 * Called when another entity collides with this one. Bullets will die
	 * whenever they collide with anything.
	 */
	public void collidesWith(EntityTypes entityType) {
		if (entityType != EntityTypes.PLAYER) {
			die();
		}
	}

	/**
	 * Kill this bullet, ending its
	 * run loop and removing it from the board.
	 */
	public void die() {
		m_board.removeEntity(this);
		m_active = false;
	}

	/**
	 * Returns a sprite representation of the bullet.
	 * 
	 * @return this Bullet's EntitySprite
	 */
	public EntitySprite getSprite() {
		return m_sprite;
	}

	/**
	 * Return the location of the bullet's center point.
	 * 
	 * @param an int array of the Bullet's x,y coords
	 */
	public int[] getLocation() {
		return new int[] { m_location.x, m_location.y };
	}

	/**
	 * Return the bounding box that surrounds the bullet.
	 * 
	 * @return this Bullet's bounding Game.Rectangle
	 */
	public Rectangle getBoundingBox() {
		return m_boundingBox;
	}

	/**
	 * This run loop allows the bullet to be run as a thread. Bullets will move
	 * on every step until they collide with something, then they die and are
	 * removed from the board.
	 */
	public void run() {
		m_active = true;
		while (m_active) {
			move();
			try {
				Thread.sleep((long) ((1.0 / 360) * 1000));
			} catch (InterruptedException e) {
			}
			Thread.yield();

		}

	}

	/**
	 * Recalculate the bounding box. Used when the location is modified.
	 */
	private void recalcBoundingBox() {
		int[] p = getLocation();
		m_boundingBox = Rectangle.fromCenter(p[0], p[1], WIDTH, HEIGHT);
	}

	/**
	 * Update our location to a new value.
	 */
	public void updateLocation(int x, int y) {
		m_location.move(x, y);
		recalcBoundingBox();
	}

	/**
	 * Return entity types.
	 */
	public EntityTypes getType() {
		return m_type;
	}

}
