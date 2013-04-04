package game;
import gui.EntitySprite;
import gui.PlayerSprite;

import java.awt.Point;
import game.Rectangle;




public class Player implements Entity , Runnable {

	private Board 					m_board;
	private Point					m_location;
	private PlayerSprite 			m_sprite;
	private Rectangle 				m_boundingBox;
	private Direction				m_direction;
	
	//true if we're currently moving, false otherwise. This must never escape.
	private boolean					m_moving;
	private final EntityTypes		m_type;
	
	private final int SQUARE_SIZE = Board.TILE_SIZE;
	private final int STEP_SIZE	  = 1; //distance moved in one turn
	
	/**
	 * How much of the screen is the player allowed to move in, from the bottom.
	 */
	private final double FRACTION_PLAYABLE = 0.20;
	
	private final int BULLET_SPAWN_DX = 0;
	private final int BULLET_SPAWN_DY = -16;
	
	public static final int START_X = Board.WIDTH_PIXELS/2;
	public static final int START_Y = Board.HEIGHT_PIXELS - (Board.TILE_SIZE * 2);
	private static final double UPDATES_PER_SECOND = 60;
	
	public Player( Board board, Point location )
	{
		m_board 	= board;
		m_location 	= location;

		m_type = EntityTypes.PLAYER;
		m_sprite = new PlayerSprite(this);
		
		recalcBoundingBox();
		m_moving = false;
	}
	
	
	/**This method is called by the handler for keyboard input.
	*It should not be confused with updateLocation, which is called by the board after all movement issues have been resolved.
	*/
	public void moveToLocation( int x, int y )
	{
		//commenting out until board is implemented
		if (y>Board.HEIGHT_PIXELS*(1-FRACTION_PLAYABLE))
		m_board.move( x, y, this );
	}
	
	/**
	 * Called by board to update our current location.
	 */
	public void updateLocation( int x, int y )
	{
		m_location.move(x, y);
		recalcBoundingBox();
	}
	
	/**
	 * Start moving in a direction due to keyboard input.
	 * The Player will move in this direction until told otherwise.
	 */
	public void beginMove(Direction direction)
	{
		m_direction = direction;
		m_moving 	= true;
	}
	
	/**Stops moved initiated by beginMove. Note that the player (for now) does not need to move diagonally.**/
	public void stopMove() {
		m_moving=false;
	}


	/**
	 * Create a bullet in front of us.
	 */
	public void fire()
	{
		//commenting out until board is implemented
		System.out.println("FIRE!");
		m_board.createEntity( m_location.x + BULLET_SPAWN_DX, m_location.y + BULLET_SPAWN_DY, EntityTypes.BULLET );
	}


	/**
	 * Recalculate the bounding box based on our current location.
	 */
	private void recalcBoundingBox()
	{
		int x = m_location.x - SQUARE_SIZE / 2;
		int y = m_location.y - SQUARE_SIZE / 2;
		m_boundingBox = Rectangle.fromUpperLeft( x, y, SQUARE_SIZE, SQUARE_SIZE );
	}
	
	/**
	 * Requests movement from the board based on our current velocity.
	 * Not to be confused with moveToLocation, which requests
	 * a move directly to the given location.
	 */
	public void move() 
	{
		//business commented out until board is implemented
		//if moving is off, then don't continue with move
		if (!m_moving) return;
		if( m_direction == Direction.LEFT && m_location.x>0)
		{
			m_board.move( m_location.x - STEP_SIZE, m_location.y, this);
		}
		else if( m_direction == Direction.RIGHT && m_location.x<Board.WIDTH_PIXELS)
		{
			m_board.move( m_location.x + STEP_SIZE, m_location.y, this );			
		}
		else if( m_direction == Direction.UP && m_location.y>0 && m_location.y>Board.HEIGHT_PIXELS*(1-FRACTION_PLAYABLE))
		{
			m_board.move( m_location.x, m_location.y - STEP_SIZE, this );			
		}
		else if( m_direction == Direction.DOWN && m_location.y<Board.HEIGHT_PIXELS)
		{
			m_board.move( m_location.x, m_location.y + STEP_SIZE, this );			
		}
		recalcBoundingBox();
	}


	/**
	 * Handles collisions with other entities.
	 * The player dies if it hits a centipede and stops moving if it hits a mushroom.
	 * Bullet collisions should be impossible since player movement is side-to-side.
	 * If a bullet collision happens for some reason, we do nothing and the bullet will go away.
	 */
	public void collidesWith(Entity entity) 
	{
		if( entity.getType() == EntityTypes.CENTIPEDE )
		{
			die();
		}
		else if ( entity.getType() == EntityTypes.MUSHROOM ) 
		{
			m_moving = false;
		}
		
		
	}

	/**
	 * If we're dead, we got hit by a centipede and need to remove ourselves from the board.
	 */
	public void die() 
	{
		//commenting out until board is implemented
		m_board.move( -1, -1, this );
	}

	/**
	 * Return a graphical sprite representation of the entity.
	 */
	public EntitySprite getSprite()
	{
		return m_sprite;
	}

	/**
	 * Return our current location (center point)
	 * 
	 */
	public int[] getLocation() 
	{
		return new int[]{m_location.x, m_location.y};
	}
	/**
	 * Return our bounding box.
	 */
	public Rectangle getBoundingBox() {
		return m_boundingBox;
	}


	/**
	 * Allows Player to be run as its own thread.
	 * Player will move a certain distance every step based on human input.
	 */
	public void run() {
		while ( m_location.x != -1 ) 
		{
			move();
			try {
				Thread.sleep((long) ((1.0 / UPDATES_PER_SECOND) * 1000));
			} catch (InterruptedException e) {
			}
			Thread.yield();
		}
	}

	/**
	 * Return the type of this entity (Player).
	 */
	public EntityTypes getType() {
		return m_type;
	}

}
