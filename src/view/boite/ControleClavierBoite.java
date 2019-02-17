package view.boite;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControleClavierBoite implements KeyListener {

	private Boite boite;

	public ControleClavierBoite(Boite boite) {
		this.boite = boite;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			boite.fermer();
			break;
		case KeyEvent.VK_ENTER:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}