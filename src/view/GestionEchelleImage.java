package view;

import java.awt.Dimension;
import java.util.Observable;

public class GestionEchelleImage extends Observable {

	private double echelle;

	public GestionEchelleImage() {
		echelle = 1;
	}

	public double getEchelle() {
		return echelle;
	}

	private double getGrossissement() {
		return 0.1;
	}

	public int getPourcentage() {
		return (int) (echelle * 100);
	}

	public void zoom() {
		echelle += getGrossissement();
		setChanged();
		notifyObservers("zoom");
	}

	public void dezoom() {
		echelle -= getGrossissement();
		setChanged();
		notifyObservers("dezoom");
	}

	public void ajuster(Dimension panel, Dimension image) {
		double valeur;
		double diffX = Math.abs(image.getWidth() - panel.getWidth());
		double diffY = Math.abs(image.getHeight() - panel.getHeight());
		if (diffX >= diffY) {
			valeur = panel.getHeight() / image.getHeight();
		} else {
			valeur = panel.getWidth() / image.getWidth();
		}
		echelle = valeur;
		setChanged();
		notifyObservers("ajuster");
	}

	public void tailleReelle() {
		echelle = 1;
		setChanged();
		notifyObservers("tailleReelle");
	}
}
