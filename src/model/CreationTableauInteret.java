package model;

import java.awt.Color;

public class CreationTableauInteret {

	public static int[][] vertical(Image image) {
		Boolean parLaMoyenne = true;
		int[][] tableauInteret = new int[image.getLargeur()][image.getHauteur()];
		
		for (int y = 0; y < image.getHauteur(); y++) {
			for (int x = 0; x < image.getLargeur(); x++) {
				if(parLaMoyenne) {
					tableauInteret[x][y] = getImportanceParLaMoyenne(image, x, y);
				} else {
					tableauInteret[x][y] = getImportanceParLaDifference(image, x, y);
				}
			}
		}
		
		return tableauInteret;
	}
	
	/** Plus le chiffre est grand, plus le pixel "choque" */
	private static int getImportanceParLaMoyenne(Image image, int x, int y) {
		Color moyenne = couleurMoyenneVerticale(image, x, y);
		Color reel = image.getCouleur(x, y);

		int poidsRouge = Math.abs(moyenne.getRed() - reel.getRed());
		int poidsVert = Math.abs(moyenne.getGreen() - reel.getGreen());
		int poidsBleu = Math.abs(moyenne.getBlue() - reel.getBlue());

		return poidsRouge + poidsVert + poidsBleu;
	}

	private static Color couleurMoyenneVerticale(Image image, int x, int y) {
		if (x < 0 || x >= image.getLargeur()) {
			throw new IllegalArgumentException();
		}

		if (x == 0) {
			return image.getCouleur(x + 1, y);
		}

		if(x == image.getLargeur() - 1) {
			return image.getCouleur(x - 1, y);
		}

		Color gauche = image.getCouleur(x - 1, y);
		Color droite = image.getCouleur(x + 1, y);

		int rouge = (gauche.getRed() + droite.getRed()) / 2;
		int vert = (gauche.getGreen() + droite.getGreen()) / 2;
		int bleu = (gauche.getBlue() + droite.getBlue()) / 2;

		return new Color(rouge, vert, bleu);
	}

	/** Plus la valeur est grande, plus le pixel est important */
	private static int getImportanceParLaDifference(Image image, int x, int y) {
		int rougeG = 0, vertG = 0, bleuG = 0;
		if (x > 0) {
			rougeG = Math.abs(image.getRed(x - 1, y) - image.getRed(x, y));
			vertG = Math.abs(image.getGreen(x - 1, y) - image.getGreen(x, y));
			bleuG = Math.abs(image.getBlue(x - 1, y) - image.getBlue(x, y));
		}

		int rougeD = 0, vertD = 0, bleuD = 0;
		if (x < image.getLargeur() - 1) {
			rougeD = Math.abs(image.getRed(x, y) - image.getRed(x + 1, y));
			vertD = Math.abs(image.getGreen(x, y) - image.getGreen(x + 1, y));
			bleuD = Math.abs(image.getBlue(x, y) - image.getBlue(x + 1, y));
		}
		return rougeG + rougeD + vertG + vertD + bleuG + bleuD;
	}

	public static int[][] horizontal(Image image) {
		return null;
	}
}
