package process;

import java.awt.Color;

import model.Image;

/**
 * Cr�ation et enregistrement d'une image en noir et blanc montrant l'importance d'un pixel.
 */
public class CreationImageInteret {

	/** Un pixel noir est un pixel peu important � l'inverse d'un pixel blanc. */
	public static Image executer(Image image) {
		int[][] interets = CreationTableauInteret.executer(image);

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int y = 0; y < image.getHauteur(); y++) {
			for (int x = 0; x < image.getLargeur(); x++) {
				if (interets[x][y] > max) {
					max = interets[x][y];
				}
				if (interets[x][y] < min) {
					min = interets[x][y];
				}
			}
		}
		
		int[][] pixels = normalisation(interets, min, max);
		//String chemin = "images/resultats/" + image.getNom() + "_interet." + image.getExtension();
		
		return new Image(image.getNom(), image.getExtension(), image.getChemin(), pixels);
	}

	private static int[][] normalisation(int[][] interets, int min, int max) {
		int largeur = interets.length;
		int hauteur = interets[0].length;
		int[][] pixels = new int[largeur][hauteur];
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < largeur; x++) {
				double pourcentage = normalisationEntreZeroEtUn(interets[x][y], min, max);
				int pixel = (int) (pourcentage * 255);
				pixels[x][y] = getCouleurGrise(pixel).getRGB();
			}
		}
		return pixels;
	}

	private static double normalisationEntreZeroEtUn(int valeur, int min, int max) {
		double numerateur = valeur - min;
		double denominateur = max - min;
		return numerateur / denominateur;
	}

	private static Color getCouleurGrise(int valeur) {
		return new Color(valeur, valeur, valeur);
	}
}
