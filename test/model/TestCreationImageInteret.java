package model;

import java.io.File;

import org.junit.Test;

public class TestCreationImageInteret {
	
	@Test
	public void doit_passer() {		
		File fichier = new File("images/test_obstacle_4.png");
		Image image = new Image(fichier);
		CreationImageInteret.executer(image);
	}
}
