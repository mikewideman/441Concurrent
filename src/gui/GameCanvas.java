package gui;

import game.Board;
import game.Entity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JPanel;

/** The drawing of the actual game */
class GameCanvas extends JPanel {
	private Board theBoard;
	private AnimThread animator;

	public GameCanvas(Board board) {
		this.theBoard = board;
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
				// if (img==null)
				// System.out.println("Error: Image not loaded for "+thing);
				// else
				if (img != null)
					g2.drawImage(sprite.getImage(), box.x, box.y, box.width,
							box.height, null);
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