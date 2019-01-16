package model;

import graphe.Graphe;
import graphe.Noeud;

public class CreationGraphe {

	public static Graphe executer(Image image) {
		int[][] interet = CreationTableauInteret.vertical(image);
		Graphe graphe = new Graphe(null);

		for (int y = 0; y < image.getHauteur(); y++) {
			for (int x = 0; x < image.getLargeur(); x++) {
				Noeud noeud = new Noeud(x, y);

				graphe.addNoeud(noeud);
				
				if (x != 0) {
					
				}

				if (x != image.getLargeur() - 1) {
					
				}

			}
		}

		return graphe;
	}

}
