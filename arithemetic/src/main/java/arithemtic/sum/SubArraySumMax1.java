package arithemtic.sum;

/*Kadane's algorithm*/
public class SubArraySumMax1 {
	public static int maxSum(int[] a, int n) {
		int max_ending_here = 0;
		int max_so_far = 0;

		for (int i : a) {
			max_ending_here = Math.max(0, max_ending_here + i);
			max_so_far = Math.max(max_so_far, max_ending_here);
		}
		return max_so_far;
	}

	public static void main(String[] args) {
		int a[] = { 1, -2, 3, 10, -4, 7, 2, -5, 6 };
		System.out.println(maxSum(a, a.length));
	}
}

