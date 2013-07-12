package zhwb.pongo.test;

/**
 * 有两个容器，容积分别为A升和B升，有无限多的水，现在需要C升水。
 * 我们还有一个足够大的水缸，足够容纳C升水。起初它是空的，我们只能往水缸里倒入水，而不能倒出。 可以进行的操作是： 把一个容器灌满；
 * 把一个容器清空（容器里剩余的水全部倒掉，或者倒入水缸）； 用一个容器的水倒入另外一个容器，直到倒出水的容器空或者倒入水的容器满。
 * 问是否能够通过有限次操作，使得水缸最后恰好有C升水。
 * 
 * 输入：三个整数A, B, C，其中 0 < A , B, C <= 1000000000 输出：0或1，表示能否达到要求。
 */
public class DropWater {

	public static void main(String[] args) {
		System.out.println(can(5, 4, 1));
		System.out.println(can(5, 6, 1));
	}

	public static boolean can(int a, int b, int c) {
		if (a > 0 && b > 0 && c > 0 && b <= 1000000000 && c <= 1000000000) {
			if (c % gcd(a, b) == 0) {
				return true;
			}
		}
		return false;
	}

	private static int gcd(int a, int b) {
		if (0 == a) {
			return b;
		}
		if (0 == b) {
			return a;
		}
		if (a > b) {
			int temp = a;
			a = b;
			b = temp;
		}
		int c;
		for (c = a % b; c > 0; c = a % b) {
			a = b;
			b = c;
		}
		return b;
	}
}
