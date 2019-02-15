package application;

import controller.Controller;
import model.Modelisation;
import view.Fenetre;

public class TriMe {
	
	public static void main(String[] args) {
		Modelisation modelisation = new Modelisation();
		Controller controller = new Controller(modelisation);
		Fenetre fenetre = new Fenetre(modelisation, controller);
		
		modelisation.addObserver(fenetre);
	}
}
