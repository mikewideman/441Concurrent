package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import game.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

/** A container for the game UI and any other UI elements */
public class GUI extends JFrame {
	public GUI(Board board, Player player) {
		super();
		CentipedeCanvas canvas = new CentipedeCanvas(board);
		PlayerController controller = new PlayerController(player);

		// the canvas has focus (I hope, swing focus is pretty unpredictable)
		// events on the canvas are player control actions
		canvas.addMouseListener(controller);
		canvas.addMouseMotionListener(controller);
		canvas.addKeyListener(controller);

		this.setTitle("Centipede");
		this.setSize(board.getWidth(), board.getHeight());
		// note: may have to add size if other ui elements are added

	}

	
}
