package game;
import gui.EntitySprite;

import game.Rectangle;
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
	public game.Rectangle getBoundingBox();
	
	public EntityTypes getType();

}
