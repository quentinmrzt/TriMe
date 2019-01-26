package model;

import graphe.Noeud;

public class Element {
		
	private Element parent;
	private Noeud courant;
	private int valeur;
	private boolean estFini;

	public Element(Element element, Noeud noeud) {
		parent = element;
		courant = noeud;
		valeur = calculValeur();
		estFini = false;
	}

	public Element getParent() {
		return parent;
	}

	public Noeud getNoeudCourant() {
		return courant;
	}
	
	public int getValeur() {
		return valeur;
	}
	
	public boolean estFini() {
		return estFini;
	}

	public void setFini() {
		estFini = true;
	}
	
	private int calculValeur() {
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
