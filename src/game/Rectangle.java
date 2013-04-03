package game;
public class Rectangle {
	private int x,y,width,height;


	private Rectangle(int x, int y, int width, int height) {
		if (x < 0 || y < 0 || width < 0 || height < 0)
			return;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/** Create a rectangle from the given parameters
	 * All numbers must be positive
	 * 
	 * @param x - the top left
	 * @param y - the top left
	 * @param width - change in the positive x direction
	 * @param height - change in the positive y direction
	 */
	public static Rectangle fromUpperLeft(int x, int y, int width, int height){
		return new Rectangle(x,y,width,height);
	}
	
	/**
	 * A new rectangle where x and y and halfway between the height and width
	 * @param x the center x coordinate
	 * @param y - the center y coordinate
	 * @param width
	 * @param height
	 * @return
	 */
	public static Rectangle fromCenter(int x, int y, int width, int height){
		return new Rectangle (x-width/2, y-height/2, width, height);
	}

	/**
	 * Checks whether or not this Rectangle contains the point at the specified location (x, y).
	 * @param x2
	 * @param y2
	 * @return
	 */
	public boolean contains(int x2, int y2){
		return (x2 >= x && x2 <= x+width) && (y2 >= y && y2 <= y+height);
	}
	/**
	 * Checks whether this rectangle contains the upper left and lower right corners of other
	 * @param other
	 * @return
	 */
	public boolean contains(Rectangle other){
		return this.contains(other.getX(), other.getY()) && this.contains(other.getX()+other.getWidth(), other.getY()+other.getHeight());
	}
	
	/**
	 * Do two rectangles touch?
	 * @param other
	 * @return
	 */
	public boolean overlaps(Rectangle other){
		return !((this.y+this.height) < other.getY() || this.y > (other.getY()+other.getHeight()) || (this.x+this.width) < other.getX() || this.x > (other.getX()+other.getWidth()) );
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	public String toString(){
		return "Rectangle: ("+x+","+y+") width: "+width+", height:"+height;
	}
	
}
