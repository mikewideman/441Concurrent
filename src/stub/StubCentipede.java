package stub;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import game.Board;
import game.Centipede;
import game.Direction;
import gui.CentipedeSprite;
import gui.EntitySprite;

public class StubCentipede extends Centipede {
	private boolean isHead;
	private Direction direction;
	private CentipedeSprite mySprite;
	private Board myBoard;
	/**Generates some random properties and reports them**/
	public StubCentipede(Board b) {
		super();
		isHead = Math.random()<=0.5;
		direction = Direction.values()[(int)(Math.random()*4)];
		mySprite = new CentipedeSprite(this);	// "this" escapes!!
		this.myBoard = b;

	}

	public Direction getDirection() {
		// TODO Auto-generated method stub
		return direction;
	}
	public boolean isHead() {
		// TODO Auto-generated method stub
		return isHead;
	}
	public EntitySprite getSprite(){
		return mySprite;
	}
	public Point2D getLocation(){
		return new Point((int)(Math.random()*myBoard.getWidth()), (int)(Math.random()*myBoard.getHeight()));
	}

	public Rectangle getBoundingBox() {
		int radius = 10;
		Point2D loc = getLocation();
		return new Rectangle((int)loc.getX()-radius,(int)loc.getY()-radius,radius*2,radius*2);
	}
}
