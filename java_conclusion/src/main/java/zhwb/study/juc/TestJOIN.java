package zhwb.study.juc;

public class TestJOIN {
	public static void main(String[] args) {
		Thread th1 = new Thread(new Thred1());
		th1.start();
		try {
			th1.join(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("after join");
		}
	}
}

class Thred1 implements Runnable {

	@Override
	public void run() {
		System.out.println("Start Th1");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("End Th1");
		}

	}
}