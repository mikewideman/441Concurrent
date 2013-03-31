package stub;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import game.Board;
import game.Direction;
import game.Player;

public class StubPlayer extends Player {

	@Override
	public void fire() {
		// TODO Auto-generated method stub
		System.out.println("Player fires");
	}

	public Rectangle getBoundingBox() {
		int radius = 10;
		Point2D loc = getLocation();
		return new Rectangle((int)loc.getX()-radius,(int)loc.getY()-radius,radius*2,radius*2);
	}

	Point loc;
	public StubPlayer(Board board, Point location) {
		super(board, location);
		this.loc=location;
	}

	@Override
	public void beginMove(Direction direction) {
		// TODO Auto-generated method stub
		System.out.println("Player begins moving "+direction);
		
	}


	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return loc;
	}

	@Override
	public void moveToLocation(int x, int y) {
		// TODO Auto-generated method stub
		loc = new Point(x,y);
		System.out.println("Player moves to "+x+", "+y);
	}

	@Override
	public void stopMove() {
		// TODO Auto-generated method stub
		System.out.println("Player ends moving");
	}

}
