package com.hp.test;

public class Test1 {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		String s = "java";
		int x = 4;
		boolean b = true;
		s = s += x--
				- 2
				* 3
				+ ((Object) s instanceof String ? 3 << 9 / 6 & --x
						: 7 ^ 8 % ++x) | 9;
		System.out.println(x);
		Thread.sleep(0);
	}

	void m1() {
	}

	void m1(String s) throws Exception {
	}
}
