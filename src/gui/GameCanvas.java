package gui;

import game.Board;
import game.Entity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

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
			if (sprite.shouldBeDrawn()){
				Point2D loc = this.getLocation();
				//will be drawn to fill the bounding box
				 Rectangle box = thing.getBoundingBox();
				 System.out.println("drawign at "+box);
				 Image img= sprite.getImage();
				 if (img==null)
					 System.out.println("Error: Image not loaded");
				 else
					 g2.drawImage(sprite.getImage(), box.x,box.y,box.width,box.height,
						null);
			}
		}

	}

	class AnimThread extends Thread {
		final int fps = 60;

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