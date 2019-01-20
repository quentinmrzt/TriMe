package model;

import java.io.File;

import org.junit.Test;

public class TestCreationTableauInteret {
	
	@Test
	public void doit_passer() {
		File fichier = new File("C:\\Users\\Quentin\\Pictures\\testVertical.png");
		Image image = new Image(fichier);
		int[][] tableau = CreationTableauInteret.executer(image);
		
		for(int y=0 ; y<tableau[0].length ; y++) {
			for(int x=0 ; x<tableau.length ; x++) {
				System.out.print(" "+tableau[x][y]);
			}
			System.out.println();
		}
	}
	
	/*@Test
	public void doit_lever_exception() {
		File fichier = new File("");
		Image image = new Image(fichier);
		int[][] tableau = CreationTableauInteret.vertical(image);
	}*/
}
