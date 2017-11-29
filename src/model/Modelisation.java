package model;

import java.util.ArrayList;

import observer.Observable;
import observer.Observer;

public class Modelisation implements Observable {
	// Donnée du model
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();   
	private String chemin = "";
	
	/*public Modelisation() {
		
	}*/
	
	public void reset() {
		chemin = "";
		
		// notification pour les observeurs que le chemin a changé
		notifyObserver(chemin);
	}

	// OBSERVABLE
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}

	public void notifyObserver(String str) {
		// On maj tout les observeur
		for(Observer obs : listObserver) {
			obs.update(str);
		}
	}


	// Getteur
	public String getChemin() {
		return chemin;
	}
	
	// Setteur
	public void setChemin(String chemin) {
		this.chemin = chemin;
		
		// notification pour les observeurs que le chemin a changé
		notifyObserver(chemin);
	}
}
