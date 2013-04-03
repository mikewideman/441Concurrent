package game;

public class BoardGenerator {

	static final int playerStartY = 600;
	static final int playerStartX = 300;
	public static Board generateLevel(){
		Board board = new Board();
//		Player p = (Player) board.createEntity(playerStartX, playerStartY, EntityTypes.PLAYER);
		board.createEntity(board.WIDTH_PIXELS, board.TILE_SIZE, EntityTypes.CENTIPEDE);
		return board;
	}
}
