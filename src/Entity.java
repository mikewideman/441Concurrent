import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public interface Entity {
	
	public void move();
	
	public void collidesWith(Entity entity);
	
	public void die();
	
	public EntitySprite getSprite();
	
	public Point2D getLocation();
	
	public Rectangle2D getBoundingBox();

}
