package model;

import java.io.File;
import java.util.Observable;

public class Modelisation extends Observable {
	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(File fichier) {
		this.image = new Image(fichier);
		setChanged();
		notifyObservers();
	}
}
