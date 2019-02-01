package graphe;

public class Graphe {
	private Noeud depart;
	private Noeud arrive;
	private int hauteur, largeur;
	private Noeud[][] noeuds;

	public Graphe(int[][] tableau) {
		noeuds = creationTableauNoeuds(tableau);
		largeur = tableau.length;
		hauteur = tableau[0].length;
		depart = Noeud.getNoeudDepart();
		arrive = Noeud.getNoeudArrive();

		ajoutNoeudDepart(depart, noeuds);
		creationDesLiens(noeuds);
		ajoutNoeudArrive(arrive, noeuds);
	}
	
	private Noeud[][] creationTableauNoeuds(int[][] interet) {
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

	public Noeud getNoeudDepart() {
		return depart;
	}

	public Noeud getNoeudArrive() {
		return arrive;
	}
	
	public int getLargeur() {
		return largeur;
	}
	
	public int getHauteur() {
		return hauteur;
	}
	
	public Noeud getNoeud(int x, int y) {
		if (x == -1 && y == -1) {
			return getNoeudDepart();
		} else if (x == -2 && y == -2) {
			return getNoeudArrive();
		}
		return noeuds[x][y];
	}
}
