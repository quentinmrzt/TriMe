package model;

import java.io.File;

import org.junit.Test;

public class TestCreationGraphe {
	
	@Test
	public void doit_passer() {
		File fichier = new File("images/test.png");
		Image image = new Image(fichier);
		CreationGraphe.executer(image);
	}
}
