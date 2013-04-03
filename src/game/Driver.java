package game;

import gui.GUI;

import java.awt.Point;

import stub.StubBoard;
import stub.StubPlayer;

public class Driver {

	static final int playerStartY = 600;
	static final int playerStartX = 300;

	
	
	public static void main(String[] args) {
		Board board = new Board();
		Player p = (Player) board.createEntity(playerStartX, playerStartY, EntityTypes.PLAYER);
		board.createEntity(board.WIDTH_PIXELS, board.TILE_SIZE, EntityTypes.CENTIPEDE);
		
		GUI gui = new GUI(p,board);
		gui.setVisible(true);

	}

}
