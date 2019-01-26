package model;

import java.awt.Color;

public class CreationImageAvecDessinChemins {

	public static Image executer(Image image, Historique historique) {
		int[][] tableau = new int[image.getLargeur()][image.getHauteur()];
		
		copie(image, tableau);
		dessin(tableau, historique);
		
		return new Image(image.getNom(), image.getExtension(), tableau);
	}
	
	private static void copie(Image image, int[][] tableau) {
		for (int y=0; y<image.getHauteur(); y++) {
			for (int x=0; x<image.getLargeur(); x++) {
				tableau[x][y] = image.getPixel(x, y);
			}
		}
	}
	
	private static void dessin(int[][] tableau, Historique historique) {
		for (int iteration=0; iteration<historique.getTaille(); iteration++) {
			for (int y=0; y<historique.getHauteur(); y++) {
				tableau[historique.positionX(iteration, y)][y] = Color.RED.getRGB();
			}
		}
	}
}
