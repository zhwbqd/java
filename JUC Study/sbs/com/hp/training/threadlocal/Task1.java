package com.hp.training.threadlocal;

public class Task1 implements Runnable {

	private Processor processor;

	public Task1(Processor processor) {
		this.processor = processor;
	}

	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			processor.process();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}
	}

}
