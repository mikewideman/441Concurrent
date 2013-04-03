package game;

import java.awt.Point;
import gui.EntitySprite;
import gui.MushroomSprite;


public class Mushroom implements Entity, Runnable{


	private Board 					m_board;
	private int 					m_curHealth;
	private Point					m_location;
	private EntitySprite 			m_sprite;
	private Rectangle 				m_boundingBox;
	private final EntityTypes		m_type;
	
	//Maximum and starting health of the mushroom.
	private final int MAX_HEALTH 	= 4;
	
	//Length of one side of the Mushroom's bounding box (in pixels)
	private final int SQUARE_SIZE 	= 50;
	
	public Mushroom(Board board, Point location)
	{
		m_board 	= board;
		m_curHealth = MAX_HEALTH;
		m_location 	= location;
		m_sprite = new MushroomSprite(this);
		m_type = EntityTypes.MUSHROOM;
		recalcBoundingBox();
	}
	
	/**
	 * Recalculates the bounding box for the mushroom based on current location.
	 */
	private void recalcBoundingBox()
	{
		int x = m_location.x - SQUARE_SIZE / 2;
		int y = m_location.y - SQUARE_SIZE / 2;
		m_boundingBox = Rectangle.fromUpperLeft( x, y, SQUARE_SIZE, SQUARE_SIZE );
	}
	
	/**
	 * This Entity method does nothing for mushrooms because they cannot move.
	 */
	public void move() 
	{
		//Mushrooms do not move
		return;
	}

	/**
	 * Mushrooms get hurt if they are hit by a bullet.
	 * They ignore other collisions.
	 */
	public void collidesWith(Entity entity) 
	{
		if( entity.getType() == EntityTypes.BULLET )
		{
			die();
		}
	}

	/**
	 * Decrement health.
	 * If health is below 0, remove ourselves from the board.
	 */
	public void die() 
	{
		m_curHealth--;
		if ( m_curHealth <= 0 )
		{
			m_board.move( -1, -1, this );
		}
	}


	/**
	 * Return a sprite representation of the entity.
	 */
	public EntitySprite getSprite() 
	{
		return m_sprite;
	}

	/**
	 * Return our current location.
	 */
	public int[] getLocation()
	{
		//don't hand out the actual Point object due to concurrency issues
		return new int[]{m_location.x, m_location.y};
	}


	/**
	 * Return our bounding box.
	 * We can hand out the rectangle directly because it's immutable.
	 */
	public Rectangle getBoundingBox() 
	{
		return m_boundingBox;
	}


	/**
	 * This run loop is unnecessary, but included for consistency with other entities.
	 */
	public void run() 
	{
		while ( m_curHealth > 0 )
		{
			Thread.yield();
		}
	}
	
	/**
	 * Lives left in this mushroom
	 * According to the game:
	 * 5 = full health
	 * 1 = lowest health (still alive)
	 * outside of interval [1, #Life images] will prevent drawing
	 * @return
	 */
	public int getHealth()
	{
		return 5;
	}

	
	public EntityTypes getType() {
		return m_type;
	}

	
	public void updateLocation(int x, int y) 
	{
		m_location.x = x;
		m_location.y = y;
	}

}
