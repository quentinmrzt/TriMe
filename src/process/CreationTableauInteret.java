package process;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.Image;

/**
 * Création d'un tableau d'intérêt par rapport à une image.
 */
public class CreationTableauInteret {

	/** L'image est convertit en tableau de valeur, plus elle est grande, plus le pixel est important. */
	public static int[][] executer(Image image) {
		int[][] tableauInteret = new int[image.getLargeur()][image.getHauteur()];

		for (int y = 0; y < image.getHauteur(); y++) {
			for (int x = 0; x < image.getLargeur(); x++) {
				tableauInteret[x][y] = getImportanceParLaMoyenne(image, x, y);
			}
		}
		
		return tableauInteret;
	}

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

		if (gauche) {
			couleurs.add(image.getCouleur(x - 1, y));
		}
		if (droite) {
			couleurs.add(image.getCouleur(x + 1, y));
		}
		if (haut) {
			couleurs.add(image.getCouleur(x, y - 1));
		}
		if (bas) {
			couleurs.add(image.getCouleur(x, y + 1));
		}
		if (haut && gauche) {
			couleurs.add(image.getCouleur(x - 1, y - 1));
		}
		if (haut && droite) {
			couleurs.add(image.getCouleur(x + 1, y - 1));
		}
		if (bas && gauche) {
			couleurs.add(image.getCouleur(x - 1, y + 1));
		}
		if (bas && droite) {
			couleurs.add(image.getCouleur(x + 1, y + 1));
		}

		int rouge = 0, vert = 0, bleu = 0;
		for (Color couleur : couleurs) {
			rouge += couleur.getRed();
			vert += couleur.getGreen();
			bleu += couleur.getBlue();
		}
		rouge = rouge / couleurs.size();
		vert = vert / couleurs.size();
		bleu = bleu / couleurs.size();

		return new Color(rouge, vert, bleu);
	}
}
