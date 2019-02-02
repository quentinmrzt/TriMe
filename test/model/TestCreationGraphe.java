package model;

import java.awt.Color;
import java.io.File;

import org.junit.Test;

import algorithme.AlgoPerso;
import graphe.Graphe;
import graphe.Noeud;
import process.CreationTableauInteret;

public class TestCreationGraphe {

	@Test
	public void doit_passer() {
		
	}
	
	@Test
	public void a_supprimer() {
		File fichier = new File("images\\test_obstacle_5.png");
		fichier.getAbsolutePath();
		Image image = new Image(fichier);
		int[][] tableau = CreationTableauInteret.executer(image);
		Graphe graphe = new Graphe(tableau);

		Noeud noeud = graphe.getNoeudDepart();

		while(noeud != null) {
			System.out.print(noeud.toString()+"- ");

			int index = 0;
			while(noeud.getNoeud(index) != null) {
				System.out.print(noeud.getNoeud(index).toString()+" ");
				index++;
			}

			System.out.println("");
			noeud = noeud.getNoeud(1);
		}

		Chemin chemin = AlgoPerso.executer(graphe);

		// On dessine le chemin
		int[][] dessin = new int[image.getLargeur()][image.getHauteur()];
		for (int y=0 ; y<image.getHauteur() ; y++) {
			for (int x=0 ; x<image.getLargeur() ; x++) {
				if(chemin.getX(y) == x) {
					dessin[x][y] = Color.RED.getRGB();
				} else {
					dessin[x][y] = image.getCouleur(x, y).getRGB();
				}
			}
		}

		// Puis on l'enregistre
		//String cheminImage = "images/resultats/"+image.getNom()+"_resultat."+image.getExtension();
		new Image(image.getNom(), image.getExtension(), image.getChemin(), dessin);
	}
}
