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
		// TODO Auto-generated method stub
		if (playerImg != null)
			return;
		playerImg = readImage("Player.bmp");
	}

	public Image getImage() {
		// TODO Auto-generated method stub
		return playerImg;
	}

	@Override
	public boolean shouldBeDrawn() {
		// TODO Auto-generated method stub
		return true;
	}

}