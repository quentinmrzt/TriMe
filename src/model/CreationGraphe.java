package model;

import graphe.Graphe;
import graphe.Noeud;

public class CreationGraphe {

	public static Graphe executer(Image image) {
		int[][] interet = CreationTableauInteret.executer(image);
		Noeud[][] noeuds = new Noeud[image.getLargeur()][image.getHauteur()];

		for (int y = 0; y < image.getHauteur(); y++) {
			for (int x = 0; x < image.getLargeur(); x++) {
				noeuds[x][y] = new Noeud(x, y, interet[x][y]);
				System.out.print(interet[x][y]+" ");
			}
			System.out.println();
		}

		Noeud depart = Noeud.DEPART;
		for (int x = 0; x < image.getLargeur(); x++) {
			depart.addNoeud(noeuds[x][0]);
		}
		Graphe graphe = new Graphe(depart);

		for (int y = 0; y < image.getHauteur(); y++) {
			for (int x = 0; x < image.getLargeur(); x++) {
				if (y != image.getHauteur() - 1) {					
					addNoeudGauche(x,y,noeuds);
					addNoeudBas(x,y,noeuds);
					addNoeudDroite(x,y,image.getLargeur() - 1,noeuds);
				}
			}
		}
		
		Noeud arrive = Noeud.ARRIVE;
		for (int x = 0; x < image.getLargeur(); x++) {
			noeuds[x][image.getHauteur()-1].addNoeud(arrive);
		}

		return graphe;
	}

	private static void addNoeudGauche(int x, int y, Noeud[][] noeuds) {
		if(x > 0) {
			noeuds[x][y].addNoeud(noeuds[x - 1][y + 1]);
		}
	}

	private static void addNoeudBas(int x, int y, Noeud[][] noeuds) {
		noeuds[x][y].addNoeud(noeuds[x][y + 1]);
	}

	private static void addNoeudDroite(int x, int y, int largeurMax, Noeud[][] noeuds) {
		if(x < largeurMax) {
			noeuds[x][y].addNoeud(noeuds[x + 1][y + 1]);
		}
	}
}
