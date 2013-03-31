package gui;

import game.Board;
import game.Entity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

/** The drawing of the actual game */
class CentipedeCanvas extends JPanel {
	private Board theBoard;
	private AnimThread animator;

	public CentipedeCanvas(Board board) {
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
				g2.drawImage(sprite.getImage(), (int) loc.getX()-box.width/2, (int) loc.getY()-box.height/2,box.width,box.height,
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