package stub;

import java.awt.Point;
import java.awt.geom.Point2D;

import game.Board;
import game.Centipede;
import game.Direction;
import gui.CentipedeSprite;
import gui.EntitySprite;
import game.Rectangle;

public class StubCentipede extends Centipede {
	private boolean isHead;
	private Direction direction;
	private CentipedeSprite mySprite;
	private Board myBoard;
	/**Generates some random properties and reports them**/
	public StubCentipede(Board b) {
		super(true,b,getLocation(),Direction.LEFT,null);
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
	public int[] getLocation(){
		return new int[]{(int)(Math.random()*myBoard.getWidth()), (int)(Math.random()*myBoard.getHeight())};
	}

	public Rectangle getBoundingBox() {
		int radius = 10;
		int[] loc = getLocation();
		return Rectangle.fromUpperLeft(loc[0]-radius,loc[1]-radius,radius*2,radius*2);	}
}
