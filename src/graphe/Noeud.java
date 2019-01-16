package graphe;

public class Noeud {
	int x, y;
	private Branche brancheGauche;
	private Branche brancheBas;
	private Branche brancheDroite;
	
	public Noeud() {
		brancheGauche = null;
		brancheBas = null;
		brancheDroite = null;
	}
	
	public Noeud(int x, int y) {
		this.x = x;
		this.y = y;
		
		brancheGauche = null;
		brancheBas = null;
		brancheDroite = null;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Noeud getNoeudGauche() {
		if (existeBrancheGauche()) {
			return brancheGauche.getNoeudB();
		} else {
			return null;
		}
	}

	public Noeud getNoeudBas() {
		if (existeBrancheBas()) {
			return brancheBas.getNoeudB();
		} else {
			return null;
		}
	}
	
	public Noeud getNoeudDroite() {
		if (existeBrancheDroite()) {
			return brancheDroite.getNoeudB();
		} else {
			return null;
		}
	}

	public Branche getBrancheGauche() {
		return brancheGauche;
	}

	public Branche getBranchBas() {
		return brancheBas;
	}
	
	public Branche getBranchDroite() {
		return brancheDroite;
	}
	
	public boolean existeBrancheGauche() {
		return (brancheGauche != null);
	}

	public boolean existeBrancheBas() {
		return (brancheBas != null);
	}
	
	public boolean existeBrancheDroite() {
		return (brancheDroite != null);
	}

	public void setBrancheGauche(Branche branche) {
		this.brancheGauche = branche;
	}

	public void setBrancheBas(Branche branche) {
		this.brancheBas = branche;
	}
	
	public void setBrancheDroite(Branche branche) {
		this.brancheDroite = branche;
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
		str.append("BrancheGauche[" + existeBrancheGauche() + "] ");
		str.append("BrancheBas[" + existeBrancheBas() + "] ");
		str.append("BrancheDroite[" + existeBrancheDroite() + "] ");
		return str.toString();
	}
}
