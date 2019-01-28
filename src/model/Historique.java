package model;

import java.util.ArrayList;
import java.util.List;

import graphe.Noeud;

public class Historique {

	private List<List<Noeud>> noeuds;

	public Historique() {
		noeuds = new ArrayList<List<Noeud>>();	
	}

	public void add(List<Noeud> liste) {
		noeuds.add(liste);
	}
	
	private void parcours() {
		for (List<Noeud> liste: noeuds) {
			for (Noeud noeud: liste) {
				
			}
		}
	}

	/*public int positionX(int iteration, int y) {
		int positionAvantDecalage = noeuds[iteration][y].getX();
		int decalage = 0;

		for (int i=iteration - 1; i>=0; i--) {
			if (getNoeud(i, y).getX() <= positionAvantDecalage) {
				iteration++;
			}
		}

		int positionX = positionAvantDecalage + decalage;
		return positionX;
	}*/
}
