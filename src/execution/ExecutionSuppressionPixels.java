package execution;

import algorithme.AlgoPerso;
import graphe.Graphe;
import model.Chemin;
import model.Image;
import model.Modelisation;
import process.CreationImageAvecSuppresionUnPixel;
import process.CreationTableauInteret;

public class ExecutionSuppressionPixels extends Execution {

	private Modelisation modelisation;
	private int nombrePixels;

	public ExecutionSuppressionPixels(Modelisation modelisation, int nombrePixels) {
		super(modelisation.getTraitement());
		this.modelisation = modelisation;
		this.nombrePixels = nombrePixels;
	}

	@Override
	public void run() {
		Image nouvelleImage = modelisation.getImage();

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
		
		modelisation.setImage(nouvelleImage);

		String cheminImage = nouvelleImage.getChemin()+ "/" + modelisation.getImage().getNom() + "_resultat_suppr" + nombrePixels + "." + modelisation.getImage().getExtension();
		nouvelleImage.enregistrementImage(cheminImage);
		getTraitement().setFini(true);
	}
}
