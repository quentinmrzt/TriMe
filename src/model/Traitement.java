package model;

import java.util.Observable;

import execution.Execution;
import execution.ExecutionDessinDePixels;

public class Traitement extends Observable {

	private final int MINIMUM = 0, MAXIMUM = 100;

	private Thread thread;
	private Execution execution;
	private int iteration;

	public Traitement() {
		iteration = MINIMUM;
		thread = null;
		execution = new ExecutionDessinDePixels();
	}

	public void ajoutExecution(Execution execution) {
		this.execution = execution;
	}

	public void lancerExecution() {
		thread = new Thread(execution);
		thread.start();
	}

	public void ajoutIteration() {
		if (iteration < MAXIMUM) {
			iteration++;
			setChanged();
			notifyObservers();
		}
	}

	public Execution getExecution() {
		return execution;
	}

	public int getIteration() {
		return execution.getIteration();
	}
}
