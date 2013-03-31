package gui;

import game.Mushroom;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class MushroomSprite extends EntitySprite {
	private Mushroom myEntity;

	/**
	 * Mushrooms have 5 lives, and five images.
	 */
	private static final int LIVES = 5;
	
	/**
	 * Maps lives left to images in the interval [1, LIVES]
	 */
	private static Map<Integer, Image> imgCache;
	
	public MushroomSprite(Mushroom entityToManage) {
		super(entityToManage);
		this.myEntity=entityToManage;
	}
	@Override
	protected void cacheImages() {
		if (imgCache !=null) return;
		imgCache = new HashMap<Integer, Image>();
		BufferedImage img = readImage("Mushroom.bmp");
		//image is divided into sections == to the # lives left being the healthiest
		int subimageWidth = img.getWidth()/LIVES;
		for (int l = LIVES; l > 0; l--){
			BufferedImage lifeImg = img.getSubimage((LIVES-l)*subimageWidth, 0, subimageWidth, img.getHeight());
			imgCache.put(l, lifeImg);
		}
	}

	@Override
	public Image getImage() {
		return imgCache.get(myEntity.getHealth());
	}

	
	public boolean shouldBeDrawn() {
		// TODO Auto-generated method stub
		return super.shouldBeDrawn() && imgCache.containsKey(myEntity.getHealth());
	}

}
