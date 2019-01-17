package model;

import java.io.File;

import org.junit.Test;

import graphe.Graphe;
import graphe.Noeud;

public class TestCreationGraphe {

	@Test
	public void doit_passer() {
		File fichier = new File("D:\\Images\\1.jpg");
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
	}
}
