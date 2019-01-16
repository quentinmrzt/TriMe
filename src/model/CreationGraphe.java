package model;

import graphe.Graphe;
import graphe.Noeud;

public class CreationGraphe {
	
	
	public static Graphe executer(Image image) {
		int[][] interet = CreationTableauInteret.vertical(image);
		
		int x = 0;
		int y = 0;
		Noeud noeud = new Noeud(x, y);
		Graphe graphe = new Graphe(noeud);
		
		return graphe;
	}
	
	
}
