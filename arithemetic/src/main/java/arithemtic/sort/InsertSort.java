package arithemtic.sort;

import java.util.Arrays;

public class InsertSort {
	public static void main(String[] args) {
		Long[] unsort = { 22L, 88L, 99L, 77L, 66L, 55L, 44L, 33L, 11L };

		for (int out = 1; out < unsort.length; out++) {
			int loop = out;
			long temp = unsort[out];
			while (loop > 0 && unsort[loop - 1] >= temp) {
				unsort[loop] = unsort[loop - 1];
				--loop;
			}
			unsort[loop] = temp;
		}

		System.out.println(Arrays.asList(unsort));

	}
}
