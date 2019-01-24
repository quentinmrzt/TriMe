package graphe;

public class Graphe {
	private Noeud depart;
	private Noeud arrive;

	public Graphe(Noeud depart, Noeud arrive) {
		this.depart = depart;
		this.arrive = arrive;
	}

	public Noeud getNoeudDepart() {
		return depart;
	}

	public Noeud getNoeudArrive() {
		return arrive;
	}
}
