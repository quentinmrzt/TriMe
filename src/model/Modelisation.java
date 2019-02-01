package model;

import java.io.File;
import java.util.Observable;

import algorithme.AlgoPerso;
import graphe.Graphe;
import process.CreationImageAvecSuppresionUnPixel;
import process.CreationTableauInteret;
import process.ExecutionDessinDePixels;

public class Modelisation extends Observable {
	private Traitement traitement;
	private Image image;

	public Image getImage() {
		return image;
	}

	public Traitement getTraitement() {
		return traitement;
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
		Image nouvelleImage = this.image;

		for (int i = 0; i < nombrePixels; i++) {
			int[][] interets = CreationTableauInteret.executer(nouvelleImage);
			Graphe graphe = new Graphe(interets);
			Chemin chemin = AlgoPerso.executer(graphe);
			nouvelleImage = CreationImageAvecSuppresionUnPixel.executer(nouvelleImage, chemin);
		}

		String cheminImage = "images/resultats/" + image.getNom() + "_resultat_suppr" + nombrePixels + "." + image.getExtension();
		nouvelleImage.enregistrementImage(cheminImage);
		// setImage(image);
	}

	public void dessinDePixels(int nombrePixels) {
		traitement = new Traitement(new ExecutionDessinDePixels(image, nombrePixels));
	}

	public void rotation(int degre) {
		int largeur = image.getLargeur();
		int hauteur = image.getHauteur();

		// Rotation
		double angle_radian = -degre * Math.PI / 180.0;
		double tcos = Math.cos(angle_radian);
		double tsin = Math.sin(angle_radian);

		/* calcul de la taille de l'image de destination */
		int largeurdest = (int) Math.ceil(largeur * Math.abs(tcos) + largeur * Math.abs(tsin));
		int hauteurdest = (int) Math.ceil(hauteur * Math.abs(tsin) + hauteur * Math.abs(tcos));

		/* on redimensionne l'image */
		int[][] destination = new int[largeurdest][hauteurdest];

		/* calcul du centre des images */
		int mxdest = largeurdest / 2;
		int mydest = hauteurdest / 2;
		int mx = largeur / 2;
		int my = hauteur / 2;

		for (int j = 0; j < hauteurdest; j++) {
			for (int i = 0; i < largeurdest; i++) {
				int bx = (int) (Math.ceil(tcos * (i - mxdest) + tsin * (j - mydest) + mx));
				int by = (int) (Math.ceil(-tsin * (i - mxdest) + tcos * (j - mydest) + my));

				if (bx >= 0 && bx < largeur && by >= 0 && by < hauteur) {
					destination[i][j] = image.getCouleur(bx, by).getRGB();
				}
			}
		}

		Image nouvelleImage = new Image(image.getNom(), image.getExtension(), destination);
		
		String cheminImage = "images/resultats/" + nouvelleImage.getNom() + "_resultat_rota" + degre + "." + nouvelleImage.getExtension();		
		nouvelleImage.enregistrementImage(cheminImage);
	}
}
