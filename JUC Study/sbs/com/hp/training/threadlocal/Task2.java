package com.hp.training.threadlocal;

public class Task2 implements Runnable {
	private Processor processor;

	public Task2(Processor processor) {
		this.processor = processor;
	}

	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.currentThread().isInterrupted()) {
			processor.process();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}
	}

}
