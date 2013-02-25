package zhwb.deepcopy;

public class DeepCopyTest {
	public static void main(String[] args) {
		byte[] origin = new byte[] { 1, 2, 3 };
		byte[] copy = origin.clone();
		for (int i = 0; i < origin.length; i++) {
			System.out.print(origin[i] + "\t");
		}
		for (int i = 0; i < copy.length; i++) {
			System.out.print(copy[i] + "\t");
		}
		origin[0] = 5;
		System.out.println("after modify");
		for (int i = 0; i < origin.length; i++) {
			System.out.print(origin[i] + "\t");
		}
		for (int i = 0; i < copy.length; i++) {
			System.out.print(copy[i] + "\t");
		}
	}
}
