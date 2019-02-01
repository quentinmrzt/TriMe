package graphe;

import java.util.ArrayList;
import java.util.List;

public class Noeud {
	//public final static Noeud DEPART = new Noeud(-1,-1);
	//public final static Noeud ARRIVE = new Noeud(-2,-2);
	
	int x, y, valeur;
	private List<Branche> fils;

	public Noeud(int x, int y) {
		if (x<-2 || y<-2) {
			throw new IllegalArgumentException();
		}
		
		fils = new ArrayList<Branche>();
		valeur = 0;
		this.x = x;
		this.y = y;
	}

	public Noeud(int x, int y, int valeur) {
		this(x, y);
		this.valeur = valeur;
	}
	
	@Deprecated
	public static Noeud getNoeudDepart() {
		return new Noeud(-1,-1);
	}
	
	@Deprecated
	public static Noeud getNoeudArrive() {
		return new Noeud(-2,-2);
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
	
	public int nombreFils() {
		return fils.size();
	}

	public Noeud getNoeud(int index) {
		if (existeBranche(index)) {
			return fils.get(index).getNoeudB();
		}
		return null;
	}
	
	public List<Noeud> getFils() {
		List<Noeud> tmp = new ArrayList<Noeud>();
		for(Branche branche: fils) {
			tmp.add(branche.getNoeudB());
		}
		return tmp;
	}

	public boolean existeBranche(int index) {
		return (index>=0 && index<fils.size()) && (fils.get(index) != null);
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
		str.append("Noeud[" + x + "/" + y + "/" + valeur + "] ");
		/*for(Branche branche: fils) {
			str.append("Branche[" + branche.toString() + "] ");
		}*/
		return str.toString();
	}
}
