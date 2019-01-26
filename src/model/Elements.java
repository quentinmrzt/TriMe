package model;

import java.util.ArrayList;
import java.util.List;

import graphe.Noeud;

public class Elements {
	private Element depart, arrive;
	private Element[][] tableau;
	private List<Element> liste;
	private int largeur, hauteur;
	private int taille, nombreVisite, nombreArrive;

	public Elements(int largeur, int hauteur) {
		tableau = new Element[largeur][hauteur];
		liste = new ArrayList<Element>();
		this.largeur = largeur;
		this.hauteur = hauteur;
		taille = largeur * hauteur + 2; // pour depart et arrive
		nombreVisite = 0;
		depart = null;
		arrive = null;
	}
	
	public int getLargeur() {
		return largeur;
	}
	
	public int getHauteur() {
		return hauteur;
	}
	
	public int getNombreElements() {
		return taille;
	}
	
	public int getNombreVisite() {
		return nombreVisite;
	}
	
	public Element getDepart() {
		return depart;
	}

	public Element getArrive() {
		return arrive;
	}
	
	public boolean estDepart(int x, int y) {
		return (x == -1 && y == -1);
	}
	
	public boolean estArrive(int x, int y) {
		return (x == -2 && y == -2);
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
		int x = element.getNoeudCourant().getX();
		int y = element.getNoeudCourant().getY();
		if (!existe(x, y)) {
			nombreVisite++;
		}
		if(-1 == element.getNoeudCourant().getX() && -1 == element.getNoeudCourant().getY()) {
			depart = element;
		} else if(-2 == element.getNoeudCourant().getX() && -2 == element.getNoeudCourant().getY()) {
			arrive = element;
		} else {
			tableau[x][y] = element;
			liste.add(element);
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
