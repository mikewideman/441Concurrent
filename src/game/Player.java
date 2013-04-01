package game;
import gui.EntitySprite;
import gui.PlayerSprite;

import java.awt.Point;
import java.awt.Rectangle;




public class Player implements Entity , Runnable {

	private Board 			m_board;
	private Point			m_location;
	private PlayerSprite 	m_sprite;
	private Rectangle 		m_boundingBox;
	private Direction		m_direction;
	private boolean			m_moving;
	
	private final int SQUARE_SIZE = 50;
	private final int STEP_SIZE	  = 50; //distance moved in one turn
	
	private final int BULLET_SPAWN_DX = 0;
	private final int BULLET_SPAWN_DY = -50;
	
	
	public Player( Board board, Point location )
	{
		m_board 	= board;
		m_location 	= location;

		m_sprite = new PlayerSprite(this);
		
		recalcBoundingBox();
		m_moving = false;
	}
	
	
	//This method is called by the handler for keyboard input.
	//It should not be confused with updateLocation, which is called by the board after all movement issues have been resolved.
	public void moveToLocation( int x, int y )
	{
		//commenting out until board is implemented
		//m_board.move( x, y );
	}
	
	public void updateLocation( int x, int y )
	{
		m_location.move(x, y);
		recalcBoundingBox();
	}
	
	public void beginMove(Direction direction)
	{
		m_direction = direction;
		m_moving 	= true;
	}
	
	public void endMove()
	{
		m_moving = false;
	}
	
	private void recalcBoundingBox()
	{
		int x = m_location.x - SQUARE_SIZE / 2;
		int y = m_location.y - SQUARE_SIZE / 2;
		m_boundingBox = new Rectangle( x, y, SQUARE_SIZE, SQUARE_SIZE );
	}
	
	public void fire()
	{
		//commenting out until board is implemented
		//m_board.createEntity( m_location.x + BULLET_SPAWN_DX, m_location.y + BULLET_SPAWN_DY, EntityTypes.BULLET );
	}
	
	public void move() 
	{
		//business commented out until board is implemented
		if( m_direction == Direction.LEFT )
		{
			//m_board.move( m_location.x - STEP_SIZE, m_location.y );
		}
		else if( m_direction == Direction.RIGHT )
		{
			//m_board.move( m_location.x + STEP_SIZE, m_location.y );			}
		}

	}


	public void collidesWith(Entity entity) 
	{

		
	}

	public void die() 
	{
		//commenting out until board is implemented
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
	/**Stops moved initiated by beginMove. Note that the player (for now) does not need to move diagonally.**/
	public void stopMove() {
	}

	public Rectangle getBoundingBox() {
		return m_boundingBox;
	}


	public void run() {
		while ( m_location.x != -1 ) 
		{
			move();
			Thread.yield();
		}
	}


	public int getRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

}
