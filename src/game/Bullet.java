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


	private Board 					m_board;
	private Point					m_location;
	private EntitySprite 			m_sprite;
	private Rectangle 				m_boundingBox;
	private boolean					m_active;
	private final EntityTypes		m_type;
	
	//The height/width of a bullet entity, in pixels. Useful for determining bounding box.
	private final int HEIGHT 	= Board.TILE_SIZE;
	private final int WIDTH 	= Board.TILE_SIZE/3;
	
	//The distance moved by a bullet in each step
	private final int MOVE_DX = 0;
	private final int MOVE_DY = -1;
	
	public Bullet( Board board, Point location)
	{
		m_board 	= board;
		m_location 	= location;
		m_sprite = new BulletSprite(this);
		m_type = EntityTypes.BULLET;
		
		recalcBoundingBox();
	}
	
	/**
	 * Asks the board to move
	 */
	public void move() 
	{
		m_board.move( m_location.x + MOVE_DX, m_location.y + MOVE_DY, this );
	}

	/**
	 * Called when another entity collides with this one.
	 * Bullets will die whenever they collide with anything.
	 */
	public void collidesWith(Entity entity)
	{
		//bullets disappear when they collide with anything.
		die();
	}

	/**
	 * Remove from board
	 */
	public void die() 
	{	
		//commented out until board is implemented
		m_board.move( -1, -1, this );
	}

	/**
	 * Returns a sprite representation of the entity.
	 */
	public EntitySprite getSprite() 
	{
		return m_sprite;
	}

	/**
	 * Return the location of our center point.
	 */
	public int[] getLocation() 
	{
		return new int[]{m_location.x, m_location.y};
	}

	/**
	 * Return the bounding box that surrounds the entity.
	 */
	public Rectangle getBoundingBox() 
	{	
		return m_boundingBox;
	}

	/**
	 * This run loop allows the bullet to be run as a thread.
	 * Bullets will move on every step until they are removed from the board, then they die
	 */
	public void run() {
		m_active = true;
		while ( m_active )
		{
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
	private void recalcBoundingBox()
	{
//		int x = m_location.x - WIDTH / 2;
//		int y = m_location.y - HEIGHT / 2;
//		m_boundingBox = Rectangle.fromUpperLeft( x, y, WIDTH, HEIGHT );
		int[] p = getLocation();
		m_boundingBox =  Rectangle.fromCenter( p[0], p[1], WIDTH, HEIGHT );
	}
	
	/**
	 * Update our location to a new value.
	 */
	public void updateLocation(int x, int y)
	{
		m_location.move(x, y);
		recalcBoundingBox();
	}

	/**
	 * Return entity types.
	 */
	public EntityTypes getType()
	{
		return m_type;
	}

}
