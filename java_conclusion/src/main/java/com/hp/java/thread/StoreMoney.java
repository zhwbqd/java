package com.hp.java.thread;

class BankAccount // 定义一个银行账户。它有“金额（money）”属性，并且有存钱的方法store（）。
{
	private int money = 0;

	public synchronized void store(int num) // 存钱 方法。每存一次便向屏幕输出一次存钱金额。
	{
		money = money + num;
		System.out.println(Thread.currentThread().getName() + "---money = "
				+ money);
	}
}

class Customer implements Runnable // 定义一个储户， 他有一个私有的银行账户的属性。
{
	private BankAccount acount = new BankAccount();

	public void run() {
		for (int x = 0; x < 3; ++x) {
			try // 此处调用sleep（）是为了让程序的结果出现明显的错误结果
			{
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
			acount.store(100);
		}
	}

}

public class StoreMoney {
	public static void main(String[] args) {
		Customer a = new Customer();

		Thread a1 = new Thread(a); // a1、a1、a3表示他的三个朋友通过三个路径给他打钱。即三个线程。
		Thread a2 = new Thread(a);
		Thread a3 = new Thread(a);

		a1.start();
		a2.start();
		a3.start();
	}
}