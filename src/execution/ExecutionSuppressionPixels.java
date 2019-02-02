package execution;

import algorithme.AlgoPerso;
import graphe.Graphe;
import model.Chemin;
import model.Image;
import process.CreationImageAvecSuppresionUnPixel;
import process.CreationTableauInteret;

public class ExecutionSuppressionPixels extends Execution {

	private Image image;
	private int nombrePixels;

	public ExecutionSuppressionPixels(Traitement traitement, Image image, int nombrePixels) {
		super(traitement);
		this.image = image;
		this.nombrePixels = nombrePixels;
	}

	@Override
	public void run() {
		Image nouvelleImage = this.image;

		for (int i = 0; i < nombrePixels; i++) {
			int[][] interets = CreationTableauInteret.executer(nouvelleImage);
			Graphe graphe = new Graphe(interets);
			Chemin chemin = AlgoPerso.executer(graphe);
			nouvelleImage = CreationImageAvecSuppresionUnPixel.executer(nouvelleImage, chemin);

			double numerateur = i;
			double denominateur = nombrePixels;
			int normalisation = (int) ((numerateur / denominateur) * Traitement.MAXIMUM);
			getTraitement().setPourcentage(normalisation);
		}

		String cheminImage = nouvelleImage.getChemin()+ "/" + image.getNom() + "_resultat_suppr" + nombrePixels + "." + image.getExtension();
		nouvelleImage.enregistrementImage(cheminImage);
		getTraitement().setFini(true);
	}
}
