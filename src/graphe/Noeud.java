package graphe;

import java.util.ArrayList;
import java.util.List;

public class Noeud {
	int x, y, valeur;
	private List<Branche> fils;

	public Noeud() {
		fils = new ArrayList<Branche>();
		valeur = 0;

		this.x = -1;
		this.y = -1;
	}

	public Noeud(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}

	public Noeud(int x, int y, int valeur) {
		this(x, y);
		this.valeur = valeur;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getValeur() {
		return valeur;
	}

	public Noeud getNoeud(int index) {
		if (existeBranche(index)) {
			return fils.get(index).getNoeudB();
		}
		return null;
	}

	public boolean existeBranche(int index) {
		return (index>=0 || index<fils.size()) && (fils.get(index) != null);
	}
	
	private void addBranche(Branche branche) {
		fils.add(branche);
	}

	public void addNoeud(Noeud noeud) {
		addBranche(new Branche(this, noeud));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Noeud)) {
			return false;
		}
		Noeud noeud = (Noeud) obj;
		return (this.getX() == noeud.getX() && this.getY() == noeud.getY());
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Noeud[" + x + "/" + y + "] ");
		for(Branche branche: fils) {
			str.append("Branche[" + branche.toString() + "] ");
		}
		return str.toString();
	}
}
