package model;

import java.io.File;

import org.junit.Test;

public class TestImage {
	
	@Test
	public void doit_passer() {
		
	}
	
	@Test
	public void a_supprimer() {
		File fichier = new File("C:\\Users\\Quentin\\Pictures\\test.png");
		new Image(fichier);
	}
}
