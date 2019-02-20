package execution;

import algorithme.AlgoPerso;
import graphe.Graphe;
import model.Chemin;
import model.Image;
import model.Modelisation;
import process.CreationImageAvecSuppresionUnPixel;
import process.CreationTableauInteret;

public class ExecutionSuppressionPixels extends Thread implements Runnable {

	private Traitement traitement;
	private Modelisation modelisation;
	private int nombrePixels;
	private boolean stop;

	public ExecutionSuppressionPixels(Modelisation modelisation, int nombrePixels) {
		super();
		this.traitement = modelisation.getTraitement();
		this.modelisation = modelisation;
		this.nombrePixels = nombrePixels;
	}

	public void setPourcentage(int avancement, int taille) {
		traitement.setPourcentage(calculePourcentage(avancement, taille));
	}

	private int calculePourcentage(double avancement, double taille) {
		return (int) ((avancement / taille) * Traitement.MAXIMUM);
	}

	@Override
	public void run() {
		Image nouvelleImage = modelisation.getImage();
		stop = false;
		int i = 0; 
		while(!stop && i < nombrePixels) {		
			int[][] interets = CreationTableauInteret.executer(nouvelleImage);
			Graphe graphe = new Graphe(interets);
			Chemin chemin = AlgoPerso.executer(graphe);
			nouvelleImage = CreationImageAvecSuppresionUnPixel.executer(nouvelleImage, chemin);
			setPourcentage(i, nombrePixels);
			i++;
		}
		if (!stop) {
			modelisation.setImage(nouvelleImage);
		}
		traitement.setFini(true);
	}

	public void stoper() {
		stop = true;		
	}
}
