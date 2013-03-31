package gui;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import game.*;

import javax.swing.JFrame;

/** A container for the game UI and any other UI elements */
public class GUI extends JFrame {
	public GUI(Player player, Board board) {
		super();
		
		GameCanvas canvas = new GameCanvas(board);
		PlayerController controller = new PlayerController(player);

		//listen for user input
		canvas.addMouseListener(controller);
		canvas.addMouseMotionListener(controller);
		this.addKeyListener(controller);//okay, so the canvas doesn't actually have focus

		/*Window configuration */
		this.setTitle("Centipede");
		this.setSize(board.getWidth(), board.getHeight());
		// note: may have to add size if other ui elements are added
		this.setResizable(false);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Invisible cursor (set it to a blank image)
		BufferedImage cursorImg = new BufferedImage(16, 16,
				BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				cursorImg, new Point(0, 0), "blank cursor");
		canvas.setCursor(blankCursor);

		
		add(canvas);

	}

}
