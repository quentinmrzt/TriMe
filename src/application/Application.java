package application;

import affichage.*;

public class Application {
	protected String chemin;
	protected Affichage aff;

	public Application(Affichage a) {
		chemin = new String();
		aff = a;
	}

	// GETTEUR & SETTEUR
	public void maj() {
		aff.maj();
		//mod.maj();
	}
	
	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public Affichage getAff() {
		return aff;
	}

	public void setAff(Affichage aff) {
		this.aff = aff;
	}
	
	
}
