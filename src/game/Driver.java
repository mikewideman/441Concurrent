package game;

import gui.GUI;

import java.awt.Point;

import stub.StubBoard;
import stub.StubPlayer;

public class Driver {

	static final int playerStartY = 600;
	static final int playerStartX = 300;

	
	
	public static void main(String[] args) {
		Board board = BoardGenerator.generateLevel();
		Player p = (Player) board.createEntity(playerStartX, playerStartY, EntityTypes.PLAYER);

		GUI gui = new GUI(p,board);
		gui.setVisible(true);

	}

}
