package view.boite;

import java.awt.Dimension;

import javax.swing.JProgressBar;

import execution.Traitement;

public class BarreDeChargement extends JProgressBar {

	private final int LARGEUR = 100;
	private final int HAUTEUR = 200;

	private int taille;

	public BarreDeChargement() {
		super();
		taille = 100;
		build();
	}

	private void build() {
		setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
		setMinimum(0);
		setMaximum(taille);
	}

	public void miseAJour(Traitement traitement) {
		setValue(traitement.getIteration());
	}
}
