package view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class ControleSourisBouton implements MouseListener {

	private final Color COULEURBORDURESELECTION = new Color(180, 180, 180);
	private final Color COULEURFONDSELECTION = new Color(230, 230, 230);
	private final Color COULEURFOND = Color.WHITE;

	private JButton bouton;

	public ControleSourisBouton(JButton bouton) {
		this.bouton = bouton;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(bouton.isEnabled()) {
			bouton.setBorder(new LineBorder(COULEURBORDURESELECTION, 1, true));
			bouton.setBackground(COULEURFONDSELECTION);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		bouton.setBorder(new LineBorder(COULEURFOND, 1, true));
		bouton.setBackground(COULEURFOND);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}
