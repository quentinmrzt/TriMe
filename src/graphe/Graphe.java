package graphe;

public class Graphe {
	private Noeud depart;
	private Noeud arrive;
	private int hauteur, largeur;

	public Graphe(Noeud depart, Noeud arrive, int largeur, int hauteur) {
		this.depart = depart;
		this.arrive = arrive;
		this.largeur = largeur;
		this.hauteur = hauteur;
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
}
