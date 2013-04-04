package game;

import gui.EntitySprite;

public interface Entity extends Runnable {

	/**
	 * Notify this entity that it has collided with something, and that it
	 * should act accordingly.
	 * 
	 * @param entityType
	 *            the type of thing it collided with
	 */
	public void collidesWith(EntityTypes entityType);

	/**
	 * Perform whatever action should be performed when this entity is damaged
	 * or killed during the game. Generally, die() will end this entity's
	 * running thread and remove the entity from the board. Some entities may
	 * have more complicated die() behaviors that require multiple 'deaths' for
	 * them to be removed.
	 */
	public void die();

	/**
	 * Return this entity's sprite for drawing on the UI.
	 * 
	 * @return this entity's EntitySprite
	 */
	public EntitySprite getSprite();

	/**
	 * Cause the entity to update its internal location.
	 * 
	 * @param x
	 *            new x coord
	 * @param y
	 *            new y coord
	 */
	public void updateLocation(int x, int y);

	/**
	 * Return the location of this entity on the play board as an [x,y] int
	 * array.
	 * 
	 * @return int array of entity's x,y coords
	 */
	public int[] getLocation();

	/**
	 * Return the box that defines the bounds of this entity for drawing and
	 * collision purposes.
	 * 
	 * As specified in the Rectangle doc, the location x,y is the upper left.
	 * 
	 * @return a bounding Rectangle
	 */
	public game.Rectangle getBoundingBox();

	/**
	 * Return the entity's type for collision resolution.
	 * @return this entity's EntityTypes value
	 */
	public EntityTypes getType();

}
