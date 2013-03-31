package gui;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.*;

/** Tied closely to the GUI, contains all information needed to display this **/
public abstract class EntitySprite {
	final String IMG_DIR = "img";

	// protected static Map<String, Image> cache = new HashMap<String, Image>();

	private Entity myEntity;

	public EntitySprite(Entity entityToManage) {
		// loadImages();
		this.myEntity = entityToManage;
		cacheImages();

	}

	/**
	 * Get a correctly sized image to draw. It must be loaded when it returns.
	 * Will be centered on the Entity's location.
	 */
	public abstract Image getImage();

	/**
	 * Will not draw if location is negative. Subclasses should use a logical
	 * AND with super and specify additional constraints.
	 * 
	 * @return
	 */
	public boolean shouldBeDrawn() {
		// drawn if location is positive
		Rectangle box = myEntity.getBoundingBox();
		return myEntity != null && box.x >= 0 && box.y >= 0;
	}

	/**
	 * Called during construction. Subclasses are required to cache images here
	 * using their own impementations.
	 * 
	 * @return
	 * @throws IOException
	 */
	protected abstract void cacheImages();

	//
	// /** A list of filenames of the images that will be cached on construction
	// **/
	// protected abstract String[] imagesNeeded();
	//	
	// /**
	// * Cache images from imagesNeeded() if they are not already.
	// *
	// * @param cache the cache to put the images in
	// * @throws IOException
	// * when an image cannot be loaded
	// */
	// private void loadImages() throws IOException {
	// // Map<String, Image> cache = getCache();
	// for (String filename : imagesNeeded()) {
	// if (!cache.containsKey(filename)) {
	// BufferedImage img = ImageIO.read(new File(IMG_DIR
	// + File.pathSeparator + filename));
	// cache.put(filename, img);
	// }
	// }
	// }

	/**
	 * Just a helper.
	 */
	protected BufferedImage readImage(String filename) {
		BufferedImage img = null;
		String path = IMG_DIR + File.separator + filename;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot read image file " + path);
			e.printStackTrace();
		}
		return img;
	}

}
