package model;

import java.awt.Color;
import java.text.DecimalFormat;

public class CreationImageInteret {

	public static void executer(Image image) {
		int[][] interets = CreationTableauInteret.executer(image);

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int y=0 ; y<image.getHauteur() ; y++) {
			for (int x=0 ; x<image.getLargeur() ; x++) {
				if (interets[x][y] > max) {
					max = interets[x][y];
				}
				if (interets[x][y] < min) {
					min = interets[x][y];
				}
			}
		}

		int[][] pixels = normalisation(interets, min, max);
		//statistique(pixels);
		String chemin = "images/resultats/"+image.getNom()+"_interet."+image.getExtension();
		Image.tableauToImage(pixels, chemin);
	}

	private static int[][] normalisation(int[][] interets, int min, int max) {
		int largeur = interets.length;
		int hauteur = interets[0].length;
		int[][] pixels = new int[largeur][hauteur];
		for (int y=0 ; y<hauteur ; y++) {
			for (int x=0 ; x<largeur ; x++) {
				double pourcentage = normalisation(interets[x][y], min, max);
				int pixel = (int) (pourcentage * 255);
				pixels[x][y] = couleurGrise(pixel).getRGB();
			}
		}
		return pixels;
	}

	private static double normalisation(int valeur, int min, int max) {
		double numerateur = valeur - min;
		double denominateur = max - min;
		return numerateur / denominateur;
	}

	private static Color couleurGrise(int valeur) {
		return new Color(valeur, valeur, valeur);
	}

	public static void statistique(int[][] pixels) {
		int largeur = pixels.length;
		int hauteur = pixels[0].length;
		int nombrePixels = largeur * hauteur;
		int[] occurences = new int[256];
		for (int y=0 ; y<hauteur ; y++) {
			for (int x=0 ; x<largeur ; x++) {
				int index = new Color(pixels[x][y]).getRed();
				occurences[index]++;
			}
		}

		for (int i=0 ; i<256 ; i++) {
			double test = ((double)occurences[i] / (double)nombrePixels);
			double pourcentage = test * 100;
			
			String nombre = new DecimalFormat("#0.00").format(pourcentage);
			
			System.out.println(i + " = " + nombre + "%");
		}
	}

}
