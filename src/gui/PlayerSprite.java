package gui;

import game.Player;

import java.awt.Image;

public class PlayerSprite extends EntitySprite {
	private Player myEntity;
	private static Image playerImg;

	public PlayerSprite(Player entityToManage) {
		super(entityToManage);
		this.myEntity = entityToManage;
	}

	protected void cacheImages() {
		if (playerImg != null)
			return;
		playerImg = readImage("Player.bmp");
	}

	public Image getImage() {
		return playerImg;
	}

	@Override
	public boolean shouldBeDrawn() {
		return true;
	}

}
