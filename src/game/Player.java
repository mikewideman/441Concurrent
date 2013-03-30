package game;
import gui.EntitySprite;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class Player implements Entity {

	public void move() {
		// TODO Auto-generated method stub
		
	}

	public void collidesWith(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	public void die() {
		// TODO Auto-generated method stub
		
	}

	public EntitySprite getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	public Point2D getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public Rectangle2D getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** The player would like to move here. Go if possible */
	public void moveToLocation(int x, int y){
		
	}
	/**The player would like to move continuously in the specified Direction.
	 * Treat like a centipede or a bullet and proceed in that direction as long as possible or until stopMove() is called.
	 * @param direction
	 */
	public void beginMove(Direction direction){}
	
	/**Stops moved initiated by beginMove. Note that the player (for now) does not need to move diagonally.**/
	public void stopMove() {
	}
	/** The player would like to fire **/
	public void fire(){}

}
