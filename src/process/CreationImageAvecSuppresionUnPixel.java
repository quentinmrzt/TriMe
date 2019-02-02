package process;

import model.Chemin;
import model.Image;

public class CreationImageAvecSuppresionUnPixel {

	public static Image executer(Image image, Chemin chemin) {
		int[][] pixels = new int[image.getLargeur()-1][image.getHauteur()];

		for (int y = 0; y < image.getHauteur(); y++) {
			int decalage = 0;
			for (int x = 0; x < image.getLargeur() - 1; x++) {
				if (chemin.getX(y) == x) {
					decalage++;
				}
				pixels[x][y] = image.getPixel(x + decalage, y);
			}
		}
		
		return new Image(image.getNom(), image.getExtension(), image.getChemin(), pixels);
	}
}
