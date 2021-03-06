package view.boite;

import javax.swing.JDialog;
import javax.swing.JFrame;

import view.utils.CouleursConstantes;

public abstract class Boite extends JDialog {

	public Boite(JFrame parent, String titre) {
		super(parent, titre, true);
		getContentPane().setBackground(CouleursConstantes.BACKGROUNDCOLOR);
		centrerFenetre(parent);
	}

	private void centrerFenetre(JFrame parent) {
		int milieuFenetreX = parent.getLocation().x + (parent.getWidth() / 2);
		int milieuFenetreY = parent.getLocation().y + (parent.getHeight() / 2);
		int moitieBoiteX = getLargeur() / 2;
		int moitieBoiteY = getHauteur() / 2;
		setLocation(milieuFenetreX - moitieBoiteX, milieuFenetreY - moitieBoiteY);
	}

	public abstract int getLargeur();
	public abstract int getHauteur();
}
