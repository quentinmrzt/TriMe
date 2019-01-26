package model;

import graphe.Noeud;

public class Element {
		
	private Element parent;
	private Noeud courant;
	private boolean estFini;

	public Element(Element element, Noeud noeud) {
		parent = element;
		courant = noeud;
		estFini = false;
	}

	public Element getParent() {
		return parent;
	}

	public Noeud getNoeudCourant() {
		return courant;
	}

	public int getValeur() {
		int valeurParent = (parent != null) ? parent.getValeur() : 0;
		return valeurParent + courant.getValeur();
	}
	
	public boolean estFini() {
		return estFini;
	}

	public void setFini() {
		estFini = true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Element) {
			Element element = (Element) obj;
			return this.getNoeudCourant().equals(element.getNoeudCourant());
		}
		return false;
	}
}
