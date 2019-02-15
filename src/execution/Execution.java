package execution;

import java.util.Observable;

public abstract class Execution extends Observable implements Runnable {

	private Traitement traitement;
	
	public Execution(Traitement traitement) {
		super();
		this.traitement = traitement;
	}
	
	public void setFini(boolean b) {
		traitement.setFini(b);
	}
	
	public void setPourcentage(int avancement, int taille) {
		traitement.setPourcentage(calculePourcentage(avancement, taille));
	}
	
	private int calculePourcentage(double avancement, double taille) {
		return (int) ((avancement / taille) * Traitement.MAXIMUM);
	}

	@Override
	public abstract void run();
}
