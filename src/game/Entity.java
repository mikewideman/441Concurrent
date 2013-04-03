package game;
import gui.EntitySprite;

import game.Rectangle;
import java.awt.geom.Point2D;


public interface Entity extends Runnable{
	
	/*
	 * move() should be private since every Runnable Entity is going to call
	 * its move() over and over but no one else will - so no one should know
	 * anything about move().
	 */
	//TODO: remove this method
	//public void move();
	
	public void collidesWith(Entity entity);
	
	public void die();
	
	public EntitySprite getSprite();
	
	public void updateLocation(int x, int y);
	
	public int[] getLocation();
		
	/**The box to draw. 
	 * As specified in the Rectangle doc, the location x,y is the upper left.
	 * @return
	 */
	public game.Rectangle getBoundingBox();
	
	public EntityTypes getType();

}
