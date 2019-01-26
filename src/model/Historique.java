package model;

import java.util.List;

import graphe.Noeud;

public class Historique {

	private int taille, hauteur;
	private Noeud[][] noeuds;

	public Historique(int nombreSupprime, int y) {
		taille = nombreSupprime;
		hauteur = y;
		noeuds = new Noeud[taille][hauteur];
	}

	public int getTaille() {
		return taille;
	}
	
	public int getHauteur() {
		return hauteur;
	}
	
	public Noeud getNoeud(int iteration, int y) {
		return noeuds[iteration][y];
	}

	public void add(int iteration, List<Noeud> liste) {
		if (iteration < taille ) {
			int y = 0;
			for (Noeud noeud: liste) {
				noeuds[iteration][y] = noeud;
				y++;
			}
		}
	}
	
	public int positionX(int iteration, int y) {
		int positionAvantDecalage = noeuds[iteration][y].getX();
		int decalage = 0;
		
		for (int i=iteration - 1; i>=0; i--) {
			if (getNoeud(i, y).getX() <= positionAvantDecalage) {
				iteration++;
			}
		}
		
		int positionX = positionAvantDecalage + decalage;
		return positionX;
	}
}
