package execution;

import algorithme.AlgoPerso;
import graphe.Graphe;
import model.Chemin;
import model.Historique;
import model.Image;
import process.CreationImageAvecDessinChemins;
import process.CreationImageAvecSuppresionUnPixel;
import process.CreationTableauInteret;

public class ExecutionDessinDePixels extends Execution {

	private Image image;
	private int nombrePixels;
	
	public ExecutionDessinDePixels() {
		super();
	}

	public ExecutionDessinDePixels(Image image, int nombrePixels) {
		this.image = image;
		this.nombrePixels = nombrePixels;
	}

	@Override
	public void run() {
		Historique historique = new Historique(nombrePixels, image.getHauteur());
		Image nouvelleImage = this.image;

		for (int i = 0; i < nombrePixels; i++) {
			int[][] interets = CreationTableauInteret.executer(nouvelleImage);
			Graphe graphe = new Graphe(interets);
			Chemin chemin = AlgoPerso.executer(graphe);
			for (int index = 0; index < chemin.getTaille(); index++) {
				System.out.print(chemin.getX(index) + " ");
			}
			System.out.println();
			historique.add(chemin);
			nouvelleImage = CreationImageAvecSuppresionUnPixel.executer(nouvelleImage, chemin);
			//ajoutIteration();
		}

		System.out.println("-----------------------------------------------------------------------");

		historique.recalculDeLaPosition();
		nouvelleImage = CreationImageAvecDessinChemins.executer(this.image, historique);

		String cheminImage = "images/resultats/" + image.getNom() + "_resultat_dess" + nombrePixels + "." + image.getExtension();
		nouvelleImage.enregistrementImage(cheminImage);
	}

}
