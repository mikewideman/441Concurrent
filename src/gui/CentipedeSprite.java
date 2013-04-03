package gui;

import game.Centipede;
import game.Direction;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class CentipedeSprite extends EntitySprite {
	private Centipede centi;
	/** Heads can point every direction **/
	private static Map<Direction, Image> headCache;
	/** Cache for body image **/
	private static Image bodyImage;

	public CentipedeSprite(Centipede entityToManage) {
		super(entityToManage);
		centi = entityToManage;

	}

	public Image getImage() {
		// TODO Auto-generated method stub
		if (!centi.isHead())
			return bodyImage;
		else
			return headCache.get(centi.getDirection());
	}


	protected void cacheImages() {
		if (bodyImage == null) {// only need to do this once on class level
			BufferedImage body = readImage("Body.bmp");
			BufferedImage body1 = body.getSubimage(0, 0, body.getWidth() / 2,
					body.getHeight());
			bodyImage = body1; // normally you'd alternate between 2 to animate
			// but I'm lazy
		}
		if (headCache == null) {
			headCache = new HashMap<Direction, Image>();

			BufferedImage head = readImage("Head.bmp");
			int quarter = head.getHeight() / 4;
			int half = head.getWidth() / 2;
			// again, the second images are nice animations, but I'm not
			// supporting that now
			headCache.put(Direction.LEFT, head.getSubimage(0, quarter * 0,
					half, quarter));
			headCache.put(Direction.RIGHT, head.getSubimage(0, quarter * 1,
					half, quarter));
			headCache.put(Direction.DOWN, head.getSubimage(0, quarter * 2,
					half, quarter));
			headCache.put(Direction.UP, head.getSubimage(0, quarter * 3, half,
					quarter));

		}

	}

}
