package model;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import execution.ExecutionSuppressionPixels;
import execution.Traitement;

public class Modelisation extends Observable {
	private Traitement traitement;
	private Image imageOriginal, imageEnCours;
	private boolean modifie;

	public Modelisation() {
		traitement = new Traitement();
		imageOriginal = null;
		imageEnCours = null;
		modifie = false;
	}

	public Image getImage() {
		return imageEnCours;
	}

	public boolean estModifie() {
		return modifie;
	}

	public Traitement getTraitement() {
		return traitement;
	}

	public void chargerImage(File fichier) {
		imageOriginal = new Image(fichier);
		imageEnCours = imageOriginal;
		setModifie(false);
		setChanged();
		notifyObservers("chargerImage");
	}

	public void setImage(Image nouvelleImage) {
		imageEnCours = nouvelleImage;
		setModifie(true);
		setChanged();
		notifyObservers("setImage");
	}

	private void setModifie(boolean b) {
		modifie = b;
	}

	public void enregistrementImage(String cheminImage) {
		imageEnCours.enregistrementImage(cheminImage);
		setModifie(false);
		setChanged();
		notifyObservers("enregistrementImage");
	}

	public void suppressionDePixels(int nombrePixels) {
		traitement.ajoutExecution(new ExecutionSuppressionPixels(this, nombrePixels));
		traitement.lancerExecution();
	}

	public void dessinDePixels(int nombrePixels) {
		/*Execution execution = new ExecutionDessinDePixels(traitement, imageEnCours, nombrePixels);
		traitement.ajoutExecution(execution);
		traitement.lancerExecution();*/
	}

	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
		traitement.addObserver(o);
	}

	public void annulerTraitement() {
		traitement.stoperExecution();
	}
}
