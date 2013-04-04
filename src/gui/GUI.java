package gui;

import game.*;

import javax.swing.JFrame;

/** A container for the game UI and any other UI elements */
public class GUI extends JFrame {

	/**
	 * Swing serialiation uid.
	 */
	private static final long serialVersionUID = -3825544726520970140L;

	private GameCanvas canvas;

	public GUI(Player player, Board board) {
		super();

		/* Window configuration */
		this.setTitle("Centipede");
		// note: may have to add size if other ui elements are added
		this.setResizable(false);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		setBoard(board, player);

	}

	private void createCanvas(Board board, Player player) {
		if (canvas != null)
			remove(this.canvas);
		GameCanvas canvas = new GameCanvas(board);
		PlayerController controller = new PlayerController(player);

		// listen for user input
		canvas.addMouseListener(controller);
		canvas.addMouseMotionListener(controller);
		this.addKeyListener(controller);

		this.canvas = canvas;
		add(canvas);
	}

	/**
	 * Change the displayed board to a different one
	 * 
	 * @param b
	 * @param p
	 */
	public void setBoard(Board b, Player p) {
		createCanvas(b, p);
		this.setSize(Board.WIDTH_PIXELS, Board.HEIGHT_PIXELS);
	}

}
