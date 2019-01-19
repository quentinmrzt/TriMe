package model;

import graphe.Noeud;

public class Element {
	private Element parent;
	private Noeud noeud;
	
	public Element(Element parent, Noeud noeud) {
		this.parent = parent;
		this.noeud = noeud;
	}
	
	public Element getParent() {
		return parent;
	}
	
	public Noeud getNoeud() {
		return noeud;
	}
	
	public int getValeur() {
		int valeurParent = (parent != null) ? parent.getValeur() : 0;
		return valeurParent + noeud.getValeur();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Element) {
			Element element = (Element) obj;
			return this.getNoeud().equals(element.getNoeud());
		}
		return false;
	}
	
	
}
