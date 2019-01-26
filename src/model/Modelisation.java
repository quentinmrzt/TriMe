package model;

import java.io.File;
import java.util.List;
import java.util.Observable;

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

	public void suppressionDePixels(int nombrePixels) {
		Image tmp = image;
		
		for (int i=0; i<nombrePixels; i++) {
			Graphe graphe = CreationGraphe.executer(tmp);
			List<Noeud> chemin = AlgoPerso.executer(graphe);
			tmp = CreationImageAvecSuppresionUnPixel.executer(tmp, chemin);
		}
		
		String cheminImage = "images/resultats/"+tmp.getNom()+"_resultat_suppr"+nombrePixels+"."+tmp.getExtension();
		Image.ImageEnImage(tmp, cheminImage, tmp.getExtension());
	}
}
