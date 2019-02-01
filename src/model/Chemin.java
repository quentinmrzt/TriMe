package model;

import java.util.List;

import graphe.Noeud;

public class Chemin {
	
	private Noeud[] chemin;
	private int taille;

	public Chemin(List<Noeud> liste) {
		taille = liste.size();
		chemin = new Noeud[taille];
		for (Noeud noeud: liste) {
			chemin[noeud.getY()] = noeud;
		}
	}
	
	public int getTaille() {
		return taille;
	}
	
	public int getX(int y) {
		return chemin[y].getX();
	}
}
