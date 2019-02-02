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

	public ExecutionDessinDePixels(Traitement traitement, Image image, int nombrePixels) {
		super(traitement);
		this.image = image;
		this.nombrePixels = nombrePixels;
	}

	@Override
	public void run() {
		Historique historique = new Historique(nombrePixels, image.getHauteur());
		Image imageReduite = image;

		for (int i = 0; i < nombrePixels; i++) {
			int[][] interets = CreationTableauInteret.executer(imageReduite);
			Graphe graphe = new Graphe(interets);
			Chemin chemin = AlgoPerso.executer(graphe);
			historique.add(chemin);
			imageReduite = CreationImageAvecSuppresionUnPixel.executer(imageReduite, chemin);

			double numerateur = i;
			double denominateur = nombrePixels;
			int normalisation = (int) ((numerateur / denominateur) * Traitement.MAXIMUM);
			getTraitement().setPourcentage(normalisation);
		}

		historique.recalculDeLaPosition();
		Image imageDessin = CreationImageAvecDessinChemins.executer(this.image, historique);

		String cheminImageDessin = imageDessin.getChemin()+ "/" + image.getNom() + "_resultat_dess" + nombrePixels + "." + image.getExtension();
		imageDessin.enregistrementImage(cheminImageDessin);
		
		String cheminImageReduite = imageReduite.getChemin()+ "/" + image.getNom() + "_resultat_suppr" + nombrePixels + "." + image.getExtension();
		imageReduite.enregistrementImage(cheminImageReduite);

		getTraitement().setFini(true);
	}
}
