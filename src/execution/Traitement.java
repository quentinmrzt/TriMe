package execution;

import java.util.Observable;

public class Traitement extends Observable {

	public final static int MINIMUM = 0, MAXIMUM = 100;

	private Thread thread;
	private Execution execution;
	private int iteration;
	private boolean fini;

	public Traitement() {
		iteration = MINIMUM;
		thread = null;
		execution = null;
		fini = false;
	}

	public void ajoutExecution(Execution execution) {
		this.execution = execution;
		iteration = MINIMUM;
	}

	public void lancerExecution() {
		if (execution != null) {
			thread = new Thread(execution);
			thread.start();
		}
	}

	public void setPourcentage(int pourcentage) {
		if (iteration < MAXIMUM) {
			iteration = pourcentage;
			setChanged();
			notifyObservers();
		}
	}

	public int getIteration() {
		return iteration;
	}

	public void setFini(boolean b) {
		fini = b;
		if (fini) {
			iteration = MAXIMUM;
			setChanged();
			notifyObservers();
		}
	}

}
