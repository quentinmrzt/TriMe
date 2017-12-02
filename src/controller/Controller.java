package controller;

import model.*;

public class Controller {
	protected Modelisation model;
	protected String chemin;
	
	public Controller(Modelisation m) {
		model = m;
		chemin = "";
	}
	
	
	// GETTEUR
	public String getChemin() {
		return chemin;
	}

	// SETTEUR
	public void setChemin(String chemin) {
		this.chemin = chemin;
		controlChemin();
	}

	// On notifie le mod�le d'une action si le contr�le est bon
	public void controlChemin() {
		// Test de controle sur le chemin
		// ??
		// puis on l'implemente dans le model
		model.setChemin(this.getChemin());
	}
	
	// On notifie le mod�le d'une action si le contr�le est bon
	public void controlDelete() {
		// Test de controle sur le chemin
		// ??
		// puis on l'implemente dans le model
		model.deletePX();
	}
}