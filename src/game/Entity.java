package game;
import gui.EntitySprite;

import java.awt.Rectangle;
import java.awt.geom.Point2D;


public interface Entity {
	
	public void move();
	
	public void collidesWith(Entity entity);
	
	public void die();
	
	public EntitySprite getSprite();
	
	public int[] getLocation();
		
	/**The box to draw. 
	 * As specified in the Rectangle doc, the location x,y is the upper left.
	 * @return
	 */
	public Rectangle getBoundingBox();
	
	public EntityTypes getType();

}
