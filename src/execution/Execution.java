package execution;

import java.util.Observable;

public abstract class Execution extends Observable implements Runnable {

	private Traitement traitement;
	
	public Execution(Traitement traitement) {
		super();
		this.traitement = traitement;
	}
	
	public Traitement getTraitement() {
		return traitement;
	}

	@Override
	public abstract void run();
}
