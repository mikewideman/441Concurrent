package game;

import gui.GUI;

/**
 * Run a multithreaded approximation of the classic arcade game "Centipede".
 * 
 */
public class Driver {

	public static void main(String[] args) {
		Board board = BoardGenerator.generateLevel();
		Player p = (Player) board.createEntity(Player.START_X, Player.START_Y,
				EntityTypes.PLAYER);

		GUI gui = new GUI(p, board);
		gui.setVisible(true);
	}

}
