package gui;
import game.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {
	public GUI(Board board, Player player){
		CentipedeCanvas canvas = new CentipedeCanvas(board);
		
	}
	
	class CentipedeCanvas extends JPanel {
		private Board theBoard;
		public CentipedeCanvas(Board board) {
			this.theBoard=board;
		}
		
	}
}
