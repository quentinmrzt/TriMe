package model;

import java.util.ArrayList;
import java.util.List;

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

	public void add(Chemin chemin) {
		if (iteration < nombrePixelSupprime && chemin.getTaille() == hauteur) {
			for (int y=0; y<chemin.getTaille(); y++) {
				position[iteration][y] = chemin.getX(y);
				y++;
			}
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
