package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControleClavier implements KeyListener {

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_Q:
			System.exit(0);
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}