package gui;

import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import game.*;

/** Tied closely to the GUI, contains all information needed to display this **/
public abstract class EntitySprite {
	final String IMG_DIR = "img";
//	protected static Map<String, Image> cache = new HashMap<String, Image>();

	public EntitySprite(Entity entityToManage) {
//		loadImages();
		try {
			cacheImages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * Get a correctly sized image to draw. It must be loaded when it returns.
	 * Will be centered on the Entity's location.
	 */
	public abstract Image getImage();

	public abstract boolean shouldBeDrawn();
	
	/**Called during construction.
	 * Subclasses are required to cache images here using their own impementations.
	 * @return 
	 * @throws IOException 
	 */
	protected abstract void cacheImages() throws IOException;
//
//	/** A list of filenames of the images that will be cached on construction **/
//	protected abstract String[] imagesNeeded();
//	
//	/**
//	 * Cache images from imagesNeeded() if they are not already.
//	 * 
//	 * @param cache the cache to put the images in
//	 * @throws IOException
//	 *             when an image cannot be loaded
//	 */
//	private void loadImages() throws IOException {
////		Map<String, Image> cache = getCache();
//		for (String filename : imagesNeeded()) {
//			if (!cache.containsKey(filename)) {
//				BufferedImage img = ImageIO.read(new File(IMG_DIR
//						+ File.pathSeparator + filename));
//				cache.put(filename, img);
//			}
//		}
//	}
	
	/** 
	 * Just a helper.
	 */
	protected BufferedImage readImage(String filename) throws IOException{
		BufferedImage img = ImageIO.read(new File(IMG_DIR
				+ File.pathSeparator + filename));
		return img;
	}

}
