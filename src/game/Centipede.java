package game;

import gui.CentipedeSprite;
import gui.EntitySprite;

import java.awt.Point;
import game.Rectangle;

/**
 * A class which represents a Centipede segment. Centipedes are essentially a
 * list of linked nodes.
 * 
 */
public class Centipede implements Entity {

	/**
	 * The default length of the Centipede
	 */
	public static final int DEFAULT_CHAIN_LENGTH = 12;
	
	/**
	 * True if this segment is the head... only heads have thread agency
	 */
	private boolean m_isHead;

	/**
	 * True if a centipede is moving leftward (if it is moving down, it will
	 * need to turn right)
	 */
	private boolean m_movingLeftward;
	
	/*
	 * Stuff we share with a lot of other stuff
	 */
	
	private Board m_board;
	private Point m_location;
	private EntitySprite m_sprite;
	private Rectangle m_boundingBox;
	private Direction m_direction;
	
	/**
	 * The next segment in line.
	 */
	private Centipede m_nextSegment;
	
	/**
	 * True if dead - used to let the previous segment know it may lose its
	 * reference to this segment.
	 */
	private boolean m_isDead;

	/**
	 * The amount subtracted from the tile size to get the delta of movement.
	 */
	private int m_speedFactor;

	/**
	 * A decrement counter which when equal to 0 will signal the need for the
	 * speed factor to increase.
	 */
	private int m_speedCount;

	/**
	 * An increment counter which when equal to SQUARE_SIZE will signal the need
	 * for the centipede to change to moving left or right.
	 */
	private int m_vertAmount;

	/**
	 * The default decrement counter value. Should be a sufficiently large
	 * number so that a Centipede speeds up only periodically.
	 */
	private final static int DEFAULT_SPEED_COUNT = 1000;

	/**
	 * Construction of a Centipede requires a factory method in order to create
	 * all segments and link them.
	 * 
	 * @param isHead
	 *            true if this segment is the head
	 * @param board
	 *            the Board on which this centipede resides
	 * @param loc
	 *            the location of this segment
	 * @param dir
	 *            the direction in which this Centipede will travel
	 * @param nextSeg
	 *            the next Centipede segment: null if this is the tail
	 */
	private Centipede(boolean isHead, Board board, Point loc, Direction dir,
			Centipede nextSeg) {
		this.m_isHead = isHead;
		this.m_movingLeftward = true;
		this.m_board = board;
		this.m_location = loc;
		this.m_direction = dir;
		this.m_nextSegment = nextSeg;
		this.m_isDead = false;
		this.m_speedFactor = Board.TILE_SIZE-1;
		this.m_speedCount = DEFAULT_SPEED_COUNT;
		this.m_vertAmount = 0;

		// Set by the factory method
		this.m_sprite = null;
		this.m_boundingBox = null;

	}

	/**
	 * Generate a chain of centipedes
	 * 
	 * @param board
	 * @param p
	 * @param nRemain
	 *            number of centipedes in the chain to make
	 * @param dir
	 *            the Direction
	 * @return
	 */
	public static Centipede generateChain(Board board, Point p, int nRemain,
			Direction dir) {
		return Centipede.generateChain(board, p, nRemain, dir, true);

	}

	/**
	 * Generate a chain of centipedes recursively
	 * 
	 * @param board
	 * @param p
	 * @param isHead
	 *            use true on first call, recursive calls are false
	 * @param nRemain
	 *            number of centipedes in the chain to make
	 * @param dir
	 *            the Direction
	 * @return
	 */
	public static Centipede generateChain(Board board, Point p, int nRemain,
			Direction dir, boolean isHead) {
		Centipede nextInChain = null;
		if (nRemain > 0)// if not the last in chain, generate more chain.
			nextInChain = Centipede.generateChain(board, new Point(p.x
					+ Board.TILE_SIZE, p.y), nRemain - 1, dir, false);
		Centipede me = new Centipede(false, board, p, dir, nextInChain);
		return me;

	}

	/**
	 * A factory method for Centipede. Builds a centipede placed horizontally
	 * with its head at the left placed at the location specified and its tail
	 * segments placed to the right. For our purposes the Centipede will always
	 * start off moving left.
	 * 
	 * @param length
	 *            The number of segments.
	 * @param board
	 *            The Board in which the Centipede will reside.
	 * @param headLocation
	 *            The location where the head segment will be.
	 * @return the new Centipede
	 */
	public static Centipede makeCentipede(int length, Board board,
			Point headLocation) {
		Centipede head = null;
		Centipede curr = null;
		for (int i = length - 1; i > 1; --i) { // Build from tail up
			// assuming we are building head at left, tail to right
			Point segmentLocation = new Point(headLocation.x + Board.TILE_SIZE
					* i, headLocation.y);
			curr = new Centipede(false, board, segmentLocation, Direction.LEFT,
					curr);
			curr.m_sprite = new CentipedeSprite(curr);
			curr.recalcBoundingBox();
			board.addCentipedeBody(curr);
		}
		head = new Centipede(true, board, headLocation, Direction.LEFT, curr);
		head.m_sprite = new CentipedeSprite(head);
		head.recalcBoundingBox();
		return head;
	}

	/**
	 * 
	 * @return true if the segment is the head of the Centipede
	 */
	public synchronized boolean isHead() {
		return this.m_isHead;
	}

	/**
	 * 
	 * @return the (value of the) direction in which this Centipede is traveling
	 */
	public Direction getDirection() {
		String value;
		synchronized (this.m_direction) {
			value = this.m_direction.toString();
		}
		return Direction.valueOf(value);
	}

	/**
	 * A Centipede will move in a line, notifying its children segments to move
	 * along with it.
	 */
	private void move() {
		if (this.m_isDead) {
			//
		} else {
			if (--this.m_speedCount == 0) {
				if (this.m_speedFactor > 0) {
					--this.m_speedFactor;
				}
				this.m_speedCount = DEFAULT_SPEED_COUNT;
			}

			int moveAmount = Board.TILE_SIZE - this.m_speedFactor;

			Direction d;
			synchronized(this.m_direction) {
				d = Direction.valueOf(this.m_direction.toString());
			}
			switch(d) {
				case LEFT:
					this.m_board.move(	this.m_location.x - moveAmount,
										this.m_location.y,
										this);
					break;
				case RIGHT:
					this.m_board.move(	this.m_location.x + moveAmount,
										this.m_location.y,
										this);
					break;
				case DOWN:
					/*
					 * If the movement rate would move you down more than the
					 * tile size, you have to stop at the tile size limit.
					 */
					boolean vertChangeBiggerThanTileSize = 
											((this.m_vertAmount + moveAmount)
												> Board.TILE_SIZE);
					moveAmount = (vertChangeBiggerThanTileSize)
										? Board.TILE_SIZE-this.m_vertAmount
										: moveAmount;
					this.m_vertAmount = (vertChangeBiggerThanTileSize)
										? 0
										: this.m_vertAmount + moveAmount;
					this.m_board.move(	this.m_location.x,
										this.m_location.y + moveAmount,
										this);
					if(vertChangeBiggerThanTileSize && this.m_nextSegment!=null) {
						// tell next segment it can move down now
						this.m_nextSegment.m_direction = Direction.DOWN;
					}
					break;
				case UP:
					/*
					 * If the movement rate would move you up more than the
					 * tile size, you have to stop at the tile size limit.
					 */
					vertChangeBiggerThanTileSize =
											((this.m_vertAmount + moveAmount)
												> Board.TILE_SIZE);
					moveAmount = (vertChangeBiggerThanTileSize)
										? Board.TILE_SIZE-this.m_vertAmount
										: moveAmount;
					this.m_vertAmount = (vertChangeBiggerThanTileSize)
										? 0
										: this.m_vertAmount + moveAmount;
					this.m_board.move(	this.m_location.x,
										this.m_location.y - moveAmount,
										this);
					if(vertChangeBiggerThanTileSize) {
						// tell next segment it can move up now
						this.m_nextSegment.m_direction = Direction.UP;
					}
					break;
			}

			// need to change direction after moving down a box size
			synchronized(this.m_direction) {
				d = Direction.valueOf(this.m_direction.toString());
			}
			if (	this.m_vertAmount == 0 && 
					(d == Direction.DOWN ||
					d == Direction.UP ))
			{
				synchronized(this.m_direction) {
					if (this.m_movingLeftward) {
						this.m_direction = Direction.RIGHT;
					} else {
						this.m_direction = Direction.LEFT;
					}
				}
				this.m_movingLeftward = !(this.m_movingLeftward);
			}

			/*
			 * Tell your next segment to move (since non-head segments don't
			 * have a thread to do that for them)
			 */
			boolean nextIsDead = false;
			if (this.m_nextSegment != null) { // lol
				synchronized (this.m_nextSegment) {
					if (this.m_nextSegment != null) { // what
						if (!this.m_nextSegment.m_isDead)
							this.m_nextSegment.move();
					} else {
						nextIsDead = true;
					}
				}
				if (nextIsDead) {
					this.m_nextSegment = null;
				}
			}

		}
	}

	/**
	 * move() until dead
	 */
	public void run() {
		while (!(this.m_isDead)) {
			move();
			try {
				Thread.sleep((long) ((1.0 / 360) * 1000));
			} catch (InterruptedException e) {
			}
			Thread.yield();

		}

	}

	/**
	 * Collision with a Bullet results in a death of the segment. Collision with
	 * a Mushroom results in a change in direction.
	 * 
	 * @param entity
	 *            the Entity with which the Centipede collided
	 */
	public void collidesWith(EntityTypes entityType) {

		if (entityType == EntityTypes.BULLET) {
			die();
		}
		else if ( entityType == EntityTypes.MUSHROOM ) 
		{
			synchronized(this.m_direction) {
				if(this.m_direction == Direction.DOWN ||
						this.m_direction == Direction.UP) {
					if (this.m_movingLeftward) {
						this.m_direction = Direction.RIGHT;
					} else {
						this.m_direction = Direction.LEFT;
					}
					this.m_movingLeftward = (!this.m_movingLeftward);
				} else {
					this.m_direction = Direction.DOWN;
					this.m_vertAmount = 1;	//hackish
				}
			}
		}

	}

	/**
	 * Kill this segment. If there is a next segment, it becomes a new head. If
	 * the segment killed is not a head, there will now be two Centipedes.
	 */
	public void die() {
		synchronized (this.m_nextSegment) {
			if (this.m_nextSegment != null) {
				this.m_nextSegment.m_isHead = true;
				this.m_nextSegment.m_direction = Direction.DOWN;

				/*
				 * Spawn a new thread and have the new head start running
				 */
			}
		}

		this.m_board.createEntity(this.m_location.x, this.m_location.y,
				EntityTypes.MUSHROOM);
		this.m_board.removeEntity(this);

		this.m_isDead = true;
	}

	/**
	 * @return the reference to the Sprite representing this segment.
	 */
	public EntitySprite getSprite() {
		// Sprites are immutable - thread safe
		return this.m_sprite;
	}

	/**
	 * Get the location of this segment.
	 * 
	 * @return an int[] formatted as {x,y}.
	 */

	public int[] getLocation() {
		Point p = m_location;
		return new int[] { p.x, p.y };
	}

	/**
	 * Get the bounding box of the Centipede.
	 * 
	 * @return the bounding box
	 */
	public Rectangle getBoundingBox() {
		// Our rectangles are immutable - thread safe
		return (this.m_boundingBox);
	}

	/**
	 * @return EntityTypes.CENTIPEDE
	 */
	public EntityTypes getType() {
		return EntityTypes.CENTIPEDE;
	}

	private void recalcBoundingBox() {
		int[] p = getLocation();
		this.m_boundingBox = Rectangle.fromCenter(p[0], p[1], Board.TILE_SIZE,
				Board.TILE_SIZE);
	}

	/**
	 * Update this Centipede's location.
	 * 
	 * @param x
	 *            the new x-coordinate
	 * @param y
	 *            the new y-coordinate
	 */
	public synchronized void updateLocation(int x, int y) {
		this.m_location.x = x;
		this.m_location.y = y;
		recalcBoundingBox();
	}

}
