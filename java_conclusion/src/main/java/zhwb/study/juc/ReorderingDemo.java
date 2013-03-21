package zhwb.study.juc;

public class ReorderingDemo {

	static int x = 0, y = 0, a = 0, b = 0;

	public static void main(String[] args) throws Exception {

		for (int i = 0; i < 100; i++) {
			x = y = a = b = 0;
			Thread one = new Thread() {
				public void run() {
					// a = 1;
					// x = b;
					x = 1;
					y = 2;
				}
			};
			Thread two = new Thread() {
				public void run() {
					// b = 1;
					// y = a;
					x = 2;
					y = 3;
				}
			};
			one.start();
			two.start();
			one.join();
			two.join();
			System.out.println(x + " " + y + " : " + i);
		}
	}

}
