package game;

import java.util.Random;

public class BoardGenerator {

	static final int MUSHROOM_START_ROW = 3;
	static final int PLAYER_SAFE_ROWS = 3;
	static final int MUSHROOM_FREQUENCY = 8;
	static final Random generator = new Random();

	/**
	 * Generate a new play board for Centipede. The new board will be randomly
	 * populated with Mushrooms between MUSHROOM_START_ROW and PLAYER_SAFE_ROWS.
	 * 
	 * @return a new board, populated with mushrooms.
	 */
	public static Board generateLevel() {
		Board board = new Board();

		board.createEntity(Board.WIDTH_PIXELS-Board.TILE_SIZE*5, Board.TILE_SIZE*2, EntityTypes.CENTIPEDE);
		for (int x = 0; x < (Board.WIDTH_PIXELS / Board.TILE_SIZE); x++) {
			for (int y = MUSHROOM_START_ROW; y < ((Board.HEIGHT_PIXELS / Board.TILE_SIZE) - PLAYER_SAFE_ROWS); y++) {
				if (generator.nextInt(10) > 8) {
					board.createEntity(x * Board.TILE_SIZE,
							y * Board.TILE_SIZE, EntityTypes.MUSHROOM);
				}
			}
		}
		return board;
	}
}

