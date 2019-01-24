package model;

import java.awt.Color;
import java.io.File;
import java.util.List;

import org.junit.Test;

import graphe.Graphe;
import graphe.Noeud;

public class TestCreationGraphe {

	@Test
	public void doit_passer() {
		File fichier = new File("images\\test_obstacle_5.png");
		fichier.getAbsolutePath();
		Image image = new Image(fichier);
		Graphe graphe = CreationGraphe.executer(image);
		
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
		
		List<Noeud> chemin = Dijkstra.executer(graphe);
		int[][] tableau = new int[image.getLargeur()][image.getHauteur()];
		for (int y=0 ; y<image.getHauteur() ; y++) {
			for (int x=0 ; x<image.getLargeur() ; x++) {
				if(chemin.get(y).getX() == x && chemin.get(y).getY() == y) {
					tableau[x][y] = Color.RED.getRGB();
				} else {
					tableau[x][y] = image.getCouleur(x, y).getRGB();
				}
			}
		}
		String cheminImage = "images/resultats/"+image.getNom()+"_resultat."+image.getExtension();
		Image.tableauEnImage(tableau, cheminImage, image.getExtension());
		
		System.out.println("GG PUTAIN");
	}
}
