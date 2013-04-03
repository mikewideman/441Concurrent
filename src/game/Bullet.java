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
	private final int HEIGHT 	= 50;
	private final int WIDTH 	= 10;
	
	//The distance moved by a bullet in each step
	private final int MOVE_DX = 0;
	private final int MOVE_DY = -10;
	
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
		//commented out until board is implemented
		//m_board.move( m_location.x + MOVE_DX, m_location.y + MOVE_DY );
	}

	public void collidesWith(Entity entity)
	{
		//bullets disappear when they collide with anything.
		die();
	}

	
	public void die() 
	{	
		//commented out until board is implemented
		//m_board.move( -1, -1 );
	}

	
	public EntitySprite getSprite() 
	{
		return m_sprite;
	}


	public int[] getLocation() 
	{
		return new int[]{m_location.x, m_location.y};
	}


	public Rectangle getBoundingBox() 
	{	
		return m_boundingBox;
	}


	public void run() {
		m_active = true;
		while ( m_active )
		{
			move();
			Thread.yield();
		}
		
	}
	
	private void recalcBoundingBox()
	{
		int x = m_location.x - WIDTH / 2;
		int y = m_location.y - HEIGHT / 2;
		m_boundingBox = Rectangle.fromUpperLeft( x, y, WIDTH, HEIGHT );
	}
	
	public void updateLocation(int x, int y)
	{
		m_location.move(x, y);
		recalcBoundingBox();
	}


	public EntityTypes getType()
	{
		return m_type;
	}

}
