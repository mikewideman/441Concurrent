package game;

public class BoardGenerator {

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
	public static Board generateLevel(){
		Board board = new Board();
//		Player p = (Player) board.createEntity(playerStartX, playerStartY, EntityTypes.PLAYER);
		board.createEntity(board.WIDTH_PIXELS-board.TILE_SIZE*5, board.TILE_SIZE*2, EntityTypes.CENTIPEDE);
		for (int r = 0; r < MUSHROOM_CONFIG.length; r++) {
			for (int c = 0; c < MUSHROOM_CONFIG[r].length; c++) {
				if (MUSHROOM_CONFIG[r][c] == 1) {
					board.createEntity(r * Board.TILE_SIZE, c * Board.TILE_SIZE, EntityTypes.MUSHROOM);
				}
			}
		}
		return board;
	}
}
