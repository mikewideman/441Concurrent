package gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.Direction;
import game.Player;
import java.awt.event.KeyListener;

/** A user input listener that controls a player **/
public class PlayerController implements KeyListener, MouseListener,
		MouseMotionListener {
	private Player thePlayer;

	/**
	 * @param thePlayer
	 *            the player to issue commands to
	 */
	public PlayerController(Player thePlayer) {
		this.thePlayer = thePlayer;
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			thePlayer.beginMove(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			thePlayer.beginMove(Direction.RIGHT);
			break;
		case KeyEvent.VK_DOWN:
			thePlayer.beginMove(Direction.DOWN);
			break;
		case KeyEvent.VK_UP:
			thePlayer.beginMove(Direction.UP);
			break;
		case KeyEvent.VK_SPACE:
			thePlayer.fire();
			break;
		}

	}

	public void keyReleased(KeyEvent e) {
		int c = e.getKeyCode();
		if (c == KeyEvent.VK_LEFT || c == KeyEvent.VK_RIGHT
				|| c == KeyEvent.VK_UP || c == KeyEvent.VK_DOWN)
			thePlayer.stopMove();

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseClicked(MouseEvent e) {


	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		thePlayer.fire();

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseMoved(MouseEvent e) {
		thePlayer.moveToLocation(e.getX(), e.getY());

	}

}
