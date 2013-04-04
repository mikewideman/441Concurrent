package stub;

import java.awt.Point;

import game.Board;
import game.Direction;
import game.Player;
import game.Rectangle;

public class StubPlayer extends Player {

	public void fire() {
		// TODO Auto-generated method stub
		System.out.println("Player fires");
	}

	public Rectangle getBoundingBox() {
		int radius = 10;
		int[] loc = getLocation();
		return Rectangle.fromUpperLeft(loc[0] - radius, loc[1] - radius,
				radius * 2, radius * 2);
	}

	Point loc;

	public StubPlayer(Board board, Point location) {
		super(board, location);
		this.loc = location;
	}

	public void beginMove(Direction direction) {
		// TODO Auto-generated method stub
		System.out.println("Player begins moving " + direction);

	}

	public int[] getLocation() {
		// TODO Auto-generated method stub
		return new int[] { loc.x, loc.y };
	}

	@Override
	public void moveToLocation(int x, int y) {
		// TODO Auto-generated method stub
		loc = new Point(x, y);
		System.out.println("Player moves to " + x + ", " + y);
	}

	@Override
	public void stopMove() {
		// TODO Auto-generated method stub
		System.out.println("Player ends moving");
	}

}
