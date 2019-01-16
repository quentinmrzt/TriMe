package model;

import graphe.Graphe;
import graphe.Noeud;

public class CreationGrapheInteret {
		
	public static Graphe vertical(int[][] tableauInteret) {
		Noeud depart = new Noeud(0,0);
		Graphe graphe = new Graphe(depart);
		
		return graphe;
	}
}
