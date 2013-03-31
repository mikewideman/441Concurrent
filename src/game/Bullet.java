
package game;
import gui.BulletSprite;
import gui.EntitySprite;


import java.awt.Point;
import java.awt.Rectangle;



public class Bullet implements Entity, Runnable {


	private Board 			m_board;
	private Point			m_location;
	private EntitySprite 		m_sprite;
	private Rectangle 		m_boundingBox;
	private boolean			m_active;
	
	private final int HEIGHT 	= 50;
	private final int WIDTH 	= 10;
	
	private final int MOVE_DX = 0;
	private final int MOVE_DY = -10;
	
	public Bullet( Board board, Point location)
	{
		m_board 	= board;
		m_location 	= location;
		m_sprite = new BulletSprite(this);
		
		recalcBoundingBox();
	}
	
	public void move() 
	{
		//commented out until board is implemented
		//m_board.move( m_location.x + MOVE_DX, m_location.y + MOVE_DY );
	}

	public void collidesWith(Entity entity)
	{
		
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


	public Point getLocation() 
	{
		return m_location;
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
		m_boundingBox = new Rectangle( x, y, WIDTH, HEIGHT );
	}
	
	public void updateLocation(int x, int y)
	{
		m_location.move(x, y);
		recalcBoundingBox();
	}

}
