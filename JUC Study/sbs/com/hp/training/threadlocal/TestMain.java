package com.hp.training.threadlocal;

public class TestMain {
	public static void main(String[] args)
	{
		Processor processor = new Processor();
		Task1 task1 = new Task1(processor);
		Task2 task2 = new Task2(processor);
		Thread thd1 = new Thread(task1);
		Thread thd2 = new Thread(task2);
		
		thd1.start();
		thd2.start();
	}

}
