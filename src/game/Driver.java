package game;

import gui.GUI;

public class Driver {
	
	static final int[][] MUSHROOM_CONFIG = {
       {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
       {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
       {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
       {0,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
       {0,0,1,0,0,0,0,0,0,0,1,1,0,0,0},
       {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
       {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
       {0,0,1,0,0,0,0,0,0,1,1,0,0,0,0},
       {0,0,1,0,0,0,0,0,0,0,1,0,0,0,0},
       {0,0,0,0,1,0,0,0,0,0,0,1,0,0,0},
       {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
       {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
       {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
       {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
       {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
       {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

	static final int playerStartY = 600;
	static final int playerStartX = 300;

	public static void main(String[] args) {
		Board board = BoardGenerator.generateLevel();
		Player p = (Player) board.createEntity(playerStartX, playerStartY, EntityTypes.PLAYER);
		for (int r = 0; r < MUSHROOM_CONFIG.length; r++) {
			for (int c = 0; c < MUSHROOM_CONFIG[r].length; c++) {
				if (MUSHROOM_CONFIG[r][c] == 1) {
					board.createEntity(r * Board.TILE_SIZE, c * Board.TILE_SIZE, EntityTypes.MUSHROOM);
				}
			}
		}
		GUI gui = new GUI(p,board);
		gui.setVisible(true);
	}

}
