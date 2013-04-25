package arithemtic.sum;

import java.util.Arrays;

public class SumMaxSubArray {
	public static int[] maxsubarray(int[] array) {

		// empty array check

		if (array.length == 0) {
			return new int[] {};
		}

		int max = 0;
		int maxhere = 0;

		// indices

		int max_start_index = 0;
		int startIndex = 0;
		int max_end_index = -1;

		for (int i = 0; i < array.length; i++) {
			if (maxhere + array[i] > 0) {
				maxhere += array[i];
			} else {
				startIndex = i + 1;
				maxhere = 0;
			}

			if (maxhere > max) {
				max = maxhere;
				max_start_index = startIndex;
				max_end_index = i;
			}

		}
		System.out.println(max);
		return Arrays.copyOfRange(array, max_start_index, max_end_index + 1);

	}

	public static void main(String[] args) {
		int a[] = { 1, -2, 3, 10, -4, 7, 2, -5 };
		int[] sub = maxsubarray(a);
		for (int i : sub) {
			System.out.print(i + ",");
		}
	}
}
