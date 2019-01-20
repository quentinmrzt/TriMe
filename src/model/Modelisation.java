package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class Modelisation extends Observable {
	private Image image;
	
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();   

	// ----------------------------------------
	// Observable
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}

	public void notifyObserver(String str) {
		// On maj tout les observeur
		for(Observer obs : listObserver) {
			obs.update(this, str);
		}
	}

	// ----------------------------------------
	// DeletePXs
	/*public void deletePXs() {
		// On enregistre l'image initiale
		tableauToImage(pixels,"Ini"+nbDelete+"px.png", true);

		for (int nbPixDel=1 ; nbPixDel<=nbDelete ; nbPixDel++) {
			System.out.println("Suppression "+nbPixDel+".");
			deletePX();
		} 

		// On enregistre l'image finale
		tableauToImage(pixelsCourant,"Finale"+nbDelete+"px.png", true);
		// On enregistre l'image avec les chemins empruntés
		tableauToImage(masqueChemins(),"Chemins"+nbDelete+"px.png", true);
	}

	public void deletePX() {
		// Copie du tableau courant car il sera modifié par la suite
		int[][] tmpImage = pixelsCourant;
		int largeur = pixelsCourant.length;
		int hauteur = pixelsCourant[0].length;

		// Création et ini du tableau d'intérêt
		int[][] interImage = new int[largeur][hauteur];
		//interImage = interestVertical(tmpImage);
		interImage = interestVertical();
		
		// Algo implicite: ?
		Graph g = new GraphImplicit(interImage, largeur, hauteur);

		// Tritopo: ?
		ArrayList<Integer> ordre = tritopo(g);

		// Bellman: ?
		ArrayList<Integer> cheminMin = Bellman(g, g.vertices()-1, g.vertices()-2, ordre);

		// Parcours et suppression des pixels inutiles
		pixelsCourant = new int[largeur-1][hauteur];
		for (int y=0 ; y<hauteur ; y++) {
			int decalPxl = 0;
			int decalChemin = 0;
			for (int x=0 ; x<largeur ; x++) {
				// Position sur une dimension
				int position = x + largeur*y;

				while(chemins[x+decalChemin][y] == true) {
					decalChemin++;
				}

				// Si la position actuelle est différente de la position du cheminMin
				if (position != cheminMin.get(y+1)) {
					// On recouvre le cheminMin
					pixelsCourant[decalPxl][y] = tmpImage[x][y];
					decalPxl++;
				} else {
					// on ini le pixel en noir
					chemins[x+decalChemin][y] = true;
				}
			}
		}
	}

	public int[][] masqueChemins() {
		int imageChemins[][] = pixels;

		for (int y=0 ; y<hauteurImage ; y++) {
			for (int x=0 ; x<largeurImage ; x++) {
				if (chemins[x][y] == true) {
					// le pixel devient noir
					imageChemins[x][y] = 0;	
				}
			}
		}

		return imageChemins;
	}*/

	public static void prioriteSuppression(int[][] tab, boolean[][] zonePxl, boolean priorite) {
		for (int y=0 ; y<tab.length ; y++) {
			for (int x=0 ; x<tab[0].length ; x++) {	   
				if(zonePxl[y][x]) {
					if(priorite) {
						tab[y][x] -= 99999;
					} else {
						tab[y][x] += 99999;
					}  
				}
			}
		}
	}

	public Image getImage() {
		return image;
	}
	
	public void setImage(File fichier) {
		this.image = new Image(fichier);
		notifyObserver(null);
	}
}
