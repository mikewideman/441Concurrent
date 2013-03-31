package stub;
import java.awt.Point;

import game.*;
import gui.*;

public class TestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Board board = new StubBoard();
		Player p = new StubPlayer(board, new Point(200,200));
		((StubBoard) board).addPlayer(p);
		GUI gui = new GUI(p,board);
		gui.setVisible(true);
		

	}

}
