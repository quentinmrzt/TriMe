package model;

import java.io.File;
import java.util.Observable;

import execution.Execution;
import execution.ExecutionDessinDePixels;
import execution.ExecutionSuppressionPixels;
import execution.Traitement;

public class Modelisation extends Observable {
	private Traitement traitement;
	private Image image;

	public Modelisation() {
		traitement = new Traitement();
		image = null;
	}

	public Image getImage() {
		return image;
	}

	public Traitement getTraitement() {
		return traitement;
	}

	public void setImage(File fichier) {
		this.image = new Image(fichier);
		setChanged();
		notifyObservers(image);
	}

	public void setImage(Image image) {
		this.image = image;
		setChanged();
		notifyObservers(image);
	}

	public void suppressionDePixels(int nombrePixels) {
		Execution execution = new ExecutionSuppressionPixels(this, nombrePixels);
		traitement.ajoutExecution(execution);
		traitement.lancerExecution();
	}

	public void dessinDePixels(int nombrePixels) {
		Execution execution = new ExecutionDessinDePixels(traitement, image, nombrePixels);
		traitement.ajoutExecution(execution);
		traitement.lancerExecution();
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

		Image nouvelleImage = new Image(image.getNom(), image.getExtension(), image.getChemin(), destination);

		String cheminImage = "images/resultats/" + nouvelleImage.getNom() + "_resultat_rota" + degre + "." + nouvelleImage.getExtension();
		nouvelleImage.enregistrementImage(cheminImage);
	}
}
