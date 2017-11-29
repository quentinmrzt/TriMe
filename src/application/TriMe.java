package application;

import controller.Controller;
import model.Modelisation;
import view.Fenetre;

public class TriMe {
	public static void main(String[] args) {
		// Notre model
		Modelisation model = new Modelisation();
		
		// Notre controler
		Controller controler = new Controller(model);
		
		// Avec �a: on a une fenetre avec un menu
		Fenetre f = new Fenetre(controler);
		
		// La fen�tre devient observeur du model
		model.addObserver(f);
				
	}
}
