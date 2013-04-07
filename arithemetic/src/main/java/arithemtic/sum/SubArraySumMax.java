package arithemtic.sum;

public class SubArraySumMax {
	public static int maxSum(int[] a, int n) {
		int sum = 0;
		int now = 0;

		for (int i = 0; i < n; i++) {
			if (now < 0) // ...
				now = a[i];
			else
				now += a[i];
			if (sum < now)
				sum = now;
		}
		return sum;
	}

	public static void main(String[] args) {
		int a[] = { 1, -2, 3, 10, -4, 7, 2, -5, 6 };
		System.out.println(maxSum(a, a.length));
	}
}

