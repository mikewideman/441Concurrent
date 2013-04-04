package game;

import gui.GUI;

/**
 * Run a multithreaded approximation of the classic arcade game "Centipede".
 * 
 */
public class Driver implements PlayerDeathListener{

	 GUI gui;
	 Player p;
	public static void main(String[] args) {
		new Driver();
	}
	Driver(){
		Board board = BoardGenerator.generateLevel();
		p = (Player) board.createEntity(Player.START_X, Player.START_Y,
				EntityTypes.PLAYER);
		p.addDeathListener(this);
		gui = new GUI(p, board);
		gui.setVisible(true);
		
	}

	public void create(){
		Board board = BoardGenerator.generateLevel();
		p = (Player) board.createEntity(Player.START_X, Player.START_Y,
				EntityTypes.PLAYER);
		p.addDeathListener(this);
		gui.setBoard(BoardGenerator.generateLevel(), p);

	}
	public void playerDied() {
		System.out.println("Restart");
		create();
		
	}

}
