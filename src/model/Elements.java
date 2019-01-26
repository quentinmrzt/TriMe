package model;

import graphe.Noeud;

public class Elements {
	private Element depart, arrive;
	private Element[][] tableau;
	private int largeur, hauteur;

	public Elements(int largeur, int hauteur) {
		tableau = new Element[largeur][hauteur];
		this.largeur = largeur;
		this.hauteur = hauteur;
		depart = null;
		arrive = null;
	}

	public Element getDepart() {
		return depart;
	}

	public Element getArrive() {
		return arrive;
	}
	
	public boolean estDepart(int x, int y) {
		return (x == Noeud.getNoeudDepart().getX() && y == Noeud.getNoeudDepart().getY());
	}
	
	public boolean estArrive(int x, int y) {
		return (x == Noeud.getNoeudArrive().getX() && y == Noeud.getNoeudArrive().getY());
	}

	public Element getElement(int x, int y) {
		if(estDepart(x, y)) {
			return depart;
		} else if (estArrive(x, y)) {
			return arrive;
		}
		return tableau[x][y];
	}

	public boolean existe(int x, int y) {
		return getElement(x, y) != null;
	}

	public void add(Element element) {
		if(element.getNoeudCourant().equals(Noeud.getNoeudDepart())) {
			depart = element;
		} else if(element.getNoeudCourant().equals(Noeud.getNoeudArrive())) {
			arrive = element;
		} else {
			int x = element.getNoeudCourant().getX();
			int y = element.getNoeudCourant().getY();
			tableau[x][y] = element;
		}
	}

	public Element lePlusPetit() {
		int min = Integer.MAX_VALUE;
		Element plusPetit = null;
		for (int y=0; y<hauteur; y++) {
			for (int x=0; x<largeur; x++) {
				if (existe(x, y) && !getElement(x, y).estFini()) {
					if (getElement(x, y).getValeur() < min) {
						plusPetit = getElement(x, y);
					}
				}
			}
		}
		return plusPetit;
	}
}
