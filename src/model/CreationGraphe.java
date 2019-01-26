package model;

import graphe.Graphe;
import graphe.Noeud;

/**
 * Création d'un graphe orienté à partir d'une image.
 */
public class CreationGraphe {

	/** Initialisation d'un graphe orienté contenant le noeud de départ et d'arrivé. */
	public static Graphe executer(Image image) {
		int[][] interets = CreationTableauInteret.executer(image);
		Noeud[][] noeuds = creationTableauNoeuds(interets);
		Noeud depart = Noeud.getNoeudDepart();
		Noeud arrive = Noeud.getNoeudArrive();

		ajoutNoeudDepart(depart, noeuds);
		creationDesLiens(noeuds);
		ajoutNoeudArrive(arrive, noeuds);

		return new Graphe(depart, arrive, noeuds);
	}

	private static Noeud[][] creationTableauNoeuds(int[][] interet) {
		int largeur = interet.length;
		int hauteur = interet[0].length;
		Noeud[][] noeuds = new Noeud[largeur][hauteur];
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < largeur; x++) {
				noeuds[x][y] = new Noeud(x, y, interet[x][y]);
			}
		}
		return noeuds;
	}

	private static void ajoutNoeudDepart(Noeud depart, Noeud[][] noeuds) {
		for (int x = 0; x < noeuds.length; x++) {
			depart.addNoeud(noeuds[x][0]);
		}
	}

	private static void creationDesLiens(Noeud[][] noeuds) {
		for (int y = 0; y < noeuds[0].length; y++) {
			for (int x = 0; x < noeuds.length; x++) {
				if (y != noeuds[0].length - 1) {
					addNoeudGauche(x, y, noeuds);
					addNoeudBas(x, y, noeuds);
					addNoeudDroite(x, y, noeuds);
				}
			}
		}
	}

	private static void addNoeudGauche(int x, int y, Noeud[][] noeuds) {
		if (x > 0) {
			noeuds[x][y].addNoeud(noeuds[x - 1][y + 1]);
		}
	}

	private static void addNoeudBas(int x, int y, Noeud[][] noeuds) {
		noeuds[x][y].addNoeud(noeuds[x][y + 1]);
	}

	private static void addNoeudDroite(int x, int y, Noeud[][] noeuds) {
		if (x < noeuds.length - 1) {
			noeuds[x][y].addNoeud(noeuds[x + 1][y + 1]);
		}
	}

	private static void ajoutNoeudArrive(Noeud arrive, Noeud[][] noeuds) {
		for (int x = 0; x < noeuds.length; x++) {
			noeuds[x][noeuds[0].length - 1].addNoeud(arrive);
		}
	}
}
