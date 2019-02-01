package model;

import execution.ExecutionDessinDePixels;

public class Traitement {
	private Thread thread;
	private ExecutionDessinDePixels execution;
	
	public Traitement(ExecutionDessinDePixels execution) {
		this.execution = execution;
		thread = new Thread(execution);
		thread.start();
	}
	
	public ExecutionDessinDePixels getExecutionDessinDePixels() {
		return execution;
	}
	
	public int getIteration() {
		return execution.getIteration();
	}
}
