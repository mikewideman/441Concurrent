package game;

import gui.GUI;

import java.awt.Point;

import stub.StubBoard;
import stub.StubPlayer;

public class Driver {


	public static void main(String[] args) {
		Board board = new StubBoard();
		Player p = new StubPlayer(board, new Point(200,200));
		((StubBoard) board).addPlayer(p);
		GUI gui = new GUI(p,board);
		gui.setVisible(true);

	}

}
