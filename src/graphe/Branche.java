package graphe;

public class Branche {
	private Noeud noeudA, noeudB;
	private int valeur;

	public Branche(Noeud noeudA, Noeud noeudB) {
		this.noeudA = noeudA;
		this.noeudB = noeudB;
		valeur = 0;
	}

	public Branche(Noeud n1, Noeud n2, int valeur) {
		this(n1, n2);
		this.valeur = valeur;
	}

	public int getValeur() {
		return valeur;
	}

	public Noeud getNoeudA() {
		return noeudA;
	}

	public Noeud getNoeudB() {
		return noeudB;
	}

	public Noeud getNoeud(int x, int y) {
		if (noeudA.getX() == x && noeudA.getY() == y) {
			return noeudA;
		} else if (noeudB.getX() == x && noeudB.getY() == y) {
			return noeudB;
		} else {
			return null;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Branche)) {
			return false;
		}
		Branche branche = (Branche) obj;
		return (this.getNoeudA().equals(branche.getNoeudA()) && this.getNoeudB().equals(branche.getNoeudB())
				|| (this.getNoeudA().equals(branche.getNoeudB()) && this.getNoeudB().equals(branche.getNoeudA())));
	}

	@Override
	public String toString() {
		return "Branche [N1=(" + noeudA + "), N2=(" + noeudB + "), valeur=" + valeur + "]";
	}
}
