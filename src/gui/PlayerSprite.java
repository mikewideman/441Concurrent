package gui;

import game.Entity;
import game.Player;

import java.awt.Image;
import java.io.IOException;

public class PlayerSprite extends EntitySprite {
	Player myEntity;
	Image playerImg;
	public PlayerSprite(Player entityToManage) {
		super(entityToManage);
		this.myEntity=entityToManage;
	}

	protected void cacheImages(){
		// TODO Auto-generated method stub
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
