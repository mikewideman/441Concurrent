package gui;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import game.*;

import javax.swing.JFrame;

/** A container for the game UI and any other UI elements */
public class GUI extends JFrame {
	private GameCanvas canvas;
	public GUI(Player player, Board board) {
		super();
		
		

		/*Window configuration */
		this.setTitle("Centipede");
		// note: may have to add size if other ui elements are added
		this.setResizable(false);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setBoard(board, player);

	}
	private void createCanvas(Board board, Player player)
	{
		if (canvas != null) remove(this.canvas);
		GameCanvas canvas = new GameCanvas(board);
		PlayerController controller = new PlayerController(player);

		//listen for user input
		canvas.addMouseListener(controller);
		canvas.addMouseMotionListener(controller);
		this.addKeyListener(controller);//okay, so the canvas doesn't actually have focus
		
		this.canvas = canvas;
		add(canvas);
	}
	
	/**
	 * Change the displayed board to a different one
	 * @param b
	 * @param p
	 */
	public void setBoard(Board b, Player p){
		createCanvas(b,p);
		this.setSize(b.WIDTH_PIXELS, b.HEIGHT_PIXELS);
	}

}
