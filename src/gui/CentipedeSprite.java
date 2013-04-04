package gui;

import game.Centipede;
import game.Direction;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class CentipedeSprite extends EntitySprite {
	private Centipede centi;
	/** Heads can point every direction 
	 * Keep an image for each
	**/
	private static Map<Direction, Image> headCache;
	/** Cache for body image **/
	private static Image bodyImage;

	public CentipedeSprite(Centipede entityToManage) {
		super(entityToManage);
		centi = entityToManage;

	}

	public Image getImage() {
		if (!centi.isHead())
			return bodyImage;
		else
			return headCache.get(centi.getDirection());
	}

	protected void cacheImages() {
		if (bodyImage == null) {	// only need to do this once on class level
			BufferedImage body = readImage("Body.bmp");
			BufferedImage body1 = body.getSubimage(0, 0, body.getWidth() / 2,
					body.getHeight());
			bodyImage = body1;
		}
		if (headCache == null) {
			headCache = new HashMap<Direction, Image>();

			BufferedImage head = readImage("Head.bmp");
			int quarter = head.getHeight() / 4;
			int half = head.getWidth() / 2;
			headCache.put(Direction.LEFT,
					head.getSubimage(0, quarter * 0, half, quarter));
			headCache.put(Direction.RIGHT,
					head.getSubimage(0, quarter * 1, half, quarter));
			headCache.put(Direction.DOWN,
					head.getSubimage(0, quarter * 2, half, quarter));
			headCache.put(Direction.UP,
					head.getSubimage(0, quarter * 3, half, quarter));

		}

	}

}
