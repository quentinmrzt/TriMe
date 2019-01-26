package graphe;

public class Graphe {
	private Noeud depart;
	private Noeud arrive;
	private int hauteur, largeur;
	private Noeud[][] noeuds;

	public Graphe(Noeud depart, Noeud arrive, Noeud[][] noeuds) {
		this.depart = depart;
		this.arrive = arrive;
		this.largeur = noeuds.length;
		this.hauteur = noeuds[0].length;
		this.noeuds = noeuds;
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
