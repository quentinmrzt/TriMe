package view;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JProgressBar;

import model.Modelisation;

public class BarreDeChargement extends JProgressBar implements Observer {

	private final int LARGEUR = 200;
	private final int HAUTEUR = 0;
	
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
    //setStringPainted(true);
	}

	@Override
	public void update(Observable obs, Object obj) {
		if (obs instanceof Modelisation) {
			Modelisation modelisation = (Modelisation) obs;
			setValue(modelisation.getTraitement().getIteration());
		}
	}
	
}
