package gui;

import game.Board;
import game.Entity;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import game.Rectangle;
import javax.swing.JPanel;

/** The drawing of the actual game */
class GameCanvas extends JPanel {
	/**
	 * Swing serialiation uid.
	 */
	private static final long serialVersionUID = -5175961592157333634L;
	private Board theBoard;
	private AnimThread animator;

	public GameCanvas(Board board) {
		this.theBoard = board;

		// Invisible cursor (set it to a blank image)
		BufferedImage cursorImg = new BufferedImage(16, 16,
				BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				cursorImg, new Point(0, 0), "blank cursor");
		this.setCursor(blankCursor);

		animator = new AnimThread();
		animator.start();
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) (g);
		// clear the graphics context
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());

		for (Entity thing : theBoard.getAllEntities()) {
			EntitySprite sprite = thing.getSprite();
			if (sprite.shouldBeDrawn()) {
				// will be drawn to fill the bounding box. Remember, box's x, y
				// is upper l
				Rectangle box = thing.getBoundingBox();
				Image img = sprite.getImage();
				if (img != null)
					g2.drawImage(sprite.getImage(), box.getX(), box.getY(),
							box.getWidth(), box.getHeight(), null);
			}
		}

	}

	class AnimThread extends Thread {
		final int fps = 60;// sleep time

		public void run() {
			while (Thread.currentThread() == animator) {
				repaint();

				try {
					Thread.sleep((long) ((1.0 / fps) * 1000));
				} catch (InterruptedException e) {
				}
			}
		}

	}

}