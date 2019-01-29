package model;

import java.util.ArrayList;
import java.util.List;

import graphe.Noeud;

public class Historique {

	private int nombrePixelSupprime, hauteur, iteration;
	private int position[][];

	public Historique(int nombrePixelSupprime, int hauteur) {
		this.iteration = 0;
		this.nombrePixelSupprime = nombrePixelSupprime;
		this.hauteur = hauteur;
		position = new int[nombrePixelSupprime][hauteur];
	}

	public int getHauteur() {
		return hauteur;
	}

	public List<Integer> getPosition(int y) {
		List<Integer> liste = new ArrayList<Integer>();
		for (int index = 0; index < nombrePixelSupprime; index++) {
			liste.add(position[index][y]);
		}
		return liste;
	}

	public void add(List<Noeud> liste) {
		if (iteration < nombrePixelSupprime && liste.size() == hauteur) {
			int y = 0;
			for (Noeud noeud : liste) {
				position[iteration][y] = noeud.getX();
				y++;
			}
			iteration++;
		}
	}

	public void recalculDeLaPosition() {
		for (int y = 0; y < hauteur; y++) {
			recalculDeLaPosition(y);
		}
	}

	private void recalculDeLaPosition(int y) {
		for (int index = 0; index < nombrePixelSupprime; index++) {
			int decalage = 0;
			for (int indexAnterieur = index - 1; indexAnterieur >= 0; indexAnterieur--) {
				if (position[indexAnterieur][y] <= position[index][y]) {
					decalage++;
				}
			}
			position[index][y] += decalage;
		}
	}
}
