package view.boite;

import javax.swing.JDialog;
import javax.swing.JFrame;

public abstract class Boite extends JDialog {

	public Boite(JFrame parent, String titre) {
		super(parent, titre, true);
	}

	public abstract void fermer();
}
