package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControleClavier implements KeyListener {

	private static boolean toucheControlActif, toucheShiftActif;
	
	public static boolean isToucheControlActif() {
		return toucheControlActif;
	}
	
	public static boolean isToucheShiftActif() {
		return toucheShiftActif;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_Q:
			System.exit(0);
			break;
		case KeyEvent.VK_CONTROL:
			toucheControlActif = true;
			break;
		case KeyEvent.VK_SHIFT:
			toucheShiftActif = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_CONTROL:
			toucheControlActif = false;
			break;
		case KeyEvent.VK_SHIFT:
			toucheShiftActif = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}