package execution;

import java.util.Observable;

public abstract class Execution extends Observable implements Runnable {

	private int iteration;

	public Execution() {
		super();
		iteration = 0;
	}

	public int getIteration() {
		return iteration;
	}

	@Override
	public abstract void run();
}
