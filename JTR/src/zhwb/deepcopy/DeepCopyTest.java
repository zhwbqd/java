package zhwb.deepcopy;

public class DeepCopyTest {
	public static void main(final String[] args) {
		byte[] origin = null;
		byte[] copy = origin.clone();

        System.out.println(origin);
        System.out.println(copy);
	}
}
