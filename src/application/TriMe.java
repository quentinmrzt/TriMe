package application;

import controller.Controller;
import model.Modelisation;
import view.Fenetre;

public class TriMe {
	
	public static void main(String[] args) {
		Modelisation modelisation = new Modelisation();
		Controller controller = new Controller(modelisation);
		Fenetre fenetre = new Fenetre(controller);
		
		modelisation.addObserver(fenetre.getZoneImage());
		modelisation.addObserver(fenetre.getScrollInformations().getZoneInformations());
		//modelisation.getTraitement().getExecutionDessinDePixels().addObserver(fenetre.getBarreDeChargement());
	}
}
