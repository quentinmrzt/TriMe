package model;

import java.io.File;

import org.junit.Test;

import process.CreationImageInteret;

public class TestCreationImageInteret {
	
	@Test
	public void doit_passer() {
		
	}
	
	@Test
	public void a_supprimer() {		
		File fichier = new File("images/test_obstacle_5.png");
		Image image = new Image(fichier);
		Image nouvelleImage = CreationImageInteret.executer(image);
		String cheminImage = "images/resultats/" + nouvelleImage.getNom() + "_resultat_TestCreationImageInteret." + nouvelleImage.getExtension();		
		nouvelleImage.enregistrementImage(cheminImage);
	}
}
