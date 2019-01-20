package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class CreationTableauInteret {

	public static int[][] executer(Image image) {
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
		Color moyenne = couleurMoyenne(image, x, y);
		Color reel = image.getCouleur(x, y);

		int poidsRouge = Math.abs(moyenne.getRed() - reel.getRed());
		int poidsVert = Math.abs(moyenne.getGreen() - reel.getGreen());
		int poidsBleu = Math.abs(moyenne.getBlue() - reel.getBlue());

		return poidsRouge + poidsVert + poidsBleu;
	}

	private static Color couleurMoyenne(Image image, int x, int y) {
		if (x < 0 || x >= image.getLargeur() || y < 0 || y >= image.getHauteur()) {
			throw new IllegalArgumentException();
		}
		
		List<Color> couleurs = new ArrayList<Color>();
		
		boolean haut = (y != 0);
		boolean droite = (x != image.getLargeur() - 1);
		boolean bas = (y != image.getHauteur() - 1);
		boolean gauche = (x != 0);
		
		if (gauche) { couleurs.add(image.getCouleur(x - 1, y)); }
		if (droite) { couleurs.add(image.getCouleur(x + 1, y)); }
		if (haut) { couleurs.add(image.getCouleur(x, y - 1)); }
		if (bas) { couleurs.add(image.getCouleur(x, y + 1)); }
		if (haut && gauche) { couleurs.add(image.getCouleur(x - 1, y - 1)); }
		if (haut && droite) { couleurs.add(image.getCouleur(x + 1, y - 1)); }
		if (bas && gauche) { couleurs.add(image.getCouleur(x - 1, y + 1)); }
		if (bas && droite) { couleurs.add(image.getCouleur(x + 1, y + 1)); }
		
		int rouge=0, vert=0, bleu=0;
		for (Color couleur: couleurs) {
			rouge += couleur.getRed();
			vert += couleur.getGreen();
			bleu += couleur.getBlue();
		}
		rouge = rouge / couleurs.size();
		vert =  vert / couleurs.size();
		bleu = bleu / couleurs.size();
		
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
