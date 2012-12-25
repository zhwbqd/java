package com.hp.training.threadlocal;

public class Processor {

	private ThreadLocal<Integer> counterTh = new ThreadLocal<Integer>() {
		protected synchronized Integer initialValue() {
			return new Integer(0);
		}
	};

	public int getCurrentValue() {
		return counterTh.get();
	}

	public int getNextValue() {
		int nextVal = counterTh.get() + 1;
		counterTh.set(nextVal);
		return nextVal;
	}

	public void process() {

		System.out.println("counter in thread " + Thread.currentThread().getId() + ":" + getNextValue());

	}

}
