package graphe;


public class Graphe {
	private int taille;
	private int profondeur;
	private Noeud depart;
	private Noeud courant;
	
	public Graphe(Noeud noeud) {
		taille = 1;
		profondeur = 1;
		depart = noeud;
		courant = noeud;
	}
	
	public Noeud getNoeudDepart() {
		return depart;
	}
	
	public Noeud getNoeudCourant() {
		return courant;
	}
	
	public int getTaille() {
		return taille;
	}
	
	public void addNoeud(Noeud noeud) {
		/*courant.setBrancheB(new Branche(courant, noeud));
		noeud.setBrancheA(new Branche(noeud, courant));
		courant = noeud;
		taille++;*/
	}
}
