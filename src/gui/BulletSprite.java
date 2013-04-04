package gui;

import game.Bullet;
import java.awt.Image;

public class BulletSprite extends EntitySprite {
	private Bullet myEntity;
	private static Image img;

	public BulletSprite(Bullet entityToManage) {
		super(entityToManage);
		this.myEntity = entityToManage;
	}

	@Override
	protected void cacheImages() {
		if (img != null)
			return;
		img = readImage("Bullet.bmp");

	}

	@Override
	public Image getImage() {
		return img;
	}

}
