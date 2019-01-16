package graphe;

public class Noeud {
	int x, y;
	private Branche brancheA;
	private Branche brancheB;

	public Noeud(int x, int y) {
		this.x = x;
		this.y = y;
		brancheA = null;
		brancheB = null;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Noeud getNoeudA() {
		if (brancheA != null) {
			return brancheA.getNoeudA();
		} else {
			return null;
		}
	}

	public Noeud getNoeudB() {
		if (brancheB != null) {
			return brancheB.getNoeudB();
		} else {
			return null;
		}
	}

	public Branche getBrancheA() {
		return brancheA;
	}

	public Branche getBranchB() {
		return brancheB;
	}
	
	public boolean existeBrancheA() {
		return (brancheA != null);
	}

	public boolean existeBrancheB() {
		return (brancheB != null);
	}

	public void setBrancheA(Branche branche) {
		if(!branche.getNoeudB().equals(this)) {
			throw new IllegalArgumentException();
		}
		this.brancheA = branche;
	}

	public void setBrancheB(Branche branche) {
		if(!branche.getNoeudA().equals(this)) {
			throw new IllegalArgumentException();
		}
		this.brancheB = branche;
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
		str.append("BrancheA[" + existeBrancheA() + "] ");
		str.append("BrancheB[" + existeBrancheB() + "] ");
		return str.toString();
	}
}
