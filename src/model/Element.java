package model;

import graphe.Noeud;

public class Element {
	private Element parent;
	private Noeud courant;

	public Element(Element element, Noeud noeud) {
		parent = element;
		courant = noeud;
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Element) {
			Element element = (Element) obj;
			return this.getNoeudCourant().equals(element.getNoeudCourant());
		}
		return false;
	}
}
