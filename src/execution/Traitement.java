package execution;

import java.util.Observable;

public class Traitement extends Observable {

	public final static int MINIMUM = 0, MAXIMUM = 100;

	private Thread thread;
	private ExecutionSuppressionPixels execution;
	private int iteration;
	private boolean fini;

	public Traitement() {
		iteration = MINIMUM;
		thread = null;
		execution = null;
		fini = false;
	}

	public void ajoutExecution(ExecutionSuppressionPixels execution) {
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
			notifyObservers("setPourcentage");
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
			notifyObservers("setFini");
		}
	}

	public void stoperExecution() {
		if (execution != null) {
			execution.stoper();
		}
	}

}
