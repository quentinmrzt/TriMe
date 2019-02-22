package execution;

import algorithme.AlgoPerso;
import graphe.Graphe;
import model.Chemin;
import model.HistoriquePixels;
import model.Image;
import process.CreationImageAvecSuppresionUnPixel;
import process.CreationTableauInteret;

public class ExecutionDessinDePixels extends Execution {

	private Image image;
	private int nombrePixels;

	public ExecutionDessinDePixels(Traitement traitement, Image image, int nombrePixels) {
		super(traitement);
		this.image = image;
		this.nombrePixels = nombrePixels;
	}

	@Override
	public void run() {
		HistoriquePixels historique = new HistoriquePixels(nombrePixels, image.getHauteur());
		Image imageReduite = image;

		for (int i = 0; i < nombrePixels; i++) {
			int[][] interets = CreationTableauInteret.executer(imageReduite);
			Graphe graphe = new Graphe(interets);
			Chemin chemin = AlgoPerso.executer(graphe);
			historique.add(chemin);
			imageReduite = CreationImageAvecSuppresionUnPixel.executer(imageReduite, chemin);
			setPourcentage(i, nombrePixels);
		}

		historique.recalculDeLaPosition();
		//Image imageDessin = CreationImageAvecDessinChemins.executer(this.image, historique);

		setFini(true);
	}
}
