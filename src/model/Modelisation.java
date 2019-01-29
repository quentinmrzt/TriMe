package model;

import java.io.File;
import java.util.List;
import java.util.Observable;

import algorithme.AlgoPerso;
import graphe.Graphe;
import graphe.Noeud;

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

	public void setImage(Image image) {
		this.image = image;
		setChanged();
		notifyObservers();
	}

	public void suppressionDePixels(int nombrePixels) {
		Image image = this.image;

		for (int i = 0; i < nombrePixels; i++) {
			Graphe graphe = CreationGraphe.executer(image);
			List<Noeud> chemin = AlgoPerso.executer(graphe);
			image = CreationImageAvecSuppresionUnPixel.executer(image, chemin);
		}

		String cheminImage = "images/resultats/" + image.getNom() + "_resultat_suppr" + nombrePixels + "." + image.getExtension();
		Image.ImageEnImage(image, cheminImage, image.getExtension());

		// setImage(image);
	}

	public void dessinDePixels(int nombrePixels) {
		Historique historique = new Historique(nombrePixels, image.getHauteur());
		Image image = this.image;

		for (int i = 0; i < nombrePixels; i++) {
			Graphe graphe = CreationGraphe.executer(image);
			List<Noeud> chemin = AlgoPerso.executer(graphe);
			historique.add(chemin);
			image = CreationImageAvecSuppresionUnPixel.executer(image, chemin);
		}

		historique.recalculDeLaPosition();

		image = CreationImageAvecDessinChemins.executer(this.image, historique);

		String cheminImage = "images/resultats/" + image.getNom() + "_resultat_dess" + nombrePixels + "." + image.getExtension();
		Image.ImageEnImage(image, cheminImage, image.getExtension());
	}
}
