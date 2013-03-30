package gui;
 import java.awt.Image;
import java.awt.geom.Point2D;

import game.*;

/**Tied closely to the GUI, contains all information needed to display this**/
public abstract class EntitySprite{
	
	/**Get a correctly sized image to draw. It must be loaded when it returns.*/
	public abstract Image getImage();
	public abstract boolean shouldBeDrawn();
	public abstract Point2D getDrawingLocation();
}
