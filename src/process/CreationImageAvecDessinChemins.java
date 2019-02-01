package process;

import java.awt.Color;

import model.Historique;
import model.Image;

/**
 * Dessin des pixels supprimés depuis une image et un historique.
 */
public class CreationImageAvecDessinChemins {

	private final static Color COULEURDESSIN = Color.RED;
	
	/**
	 * Retourne une image avec l'historique dessiné dessus.
	 */
	public static Image executer(Image image, Historique historique) {
		int[][] tableau = copieDuTableauDepuisUneImage(image);
		dessin(tableau, historique);
		return new Image(image.getNom(), image.getExtension(), tableau);
	}

	private static int[][] copieDuTableauDepuisUneImage(Image image) {
		int[][] tableau = new int[image.getLargeur()][image.getHauteur()];
		for (int y = 0; y < image.getHauteur(); y++) {
			for (int x = 0; x < image.getLargeur(); x++) {
				tableau[x][y] = image.getPixel(x, y);
			}
		}
		return tableau;
	}

	private static void dessin(int[][] tableau, Historique historique) {
		for (int y = 0; y < historique.getHauteur(); y++) {
			for (int x : historique.getPositionsDeLaLigne(y)) {
				System.out.print(x+"/"+y+" ");
				tableau[x][y] = COULEURDESSIN.getRGB();
			}
			System.out.println();
		}
	}
}
