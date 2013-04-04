package game;

import gui.GUI;

public class Driver {

	public static void main(String[] args) {
		Board board = BoardGenerator.generateLevel();
		Player p = (Player) board.createEntity(Player.START_X, Player.START_Y, EntityTypes.PLAYER);

		(new Thread(p)).start();
		GUI gui = new GUI(p,board);
		gui.setVisible(true);
	}

}
