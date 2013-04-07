package arithemtic.sort;

public class SortSummary {
	private static int compareCount;
	private static int swapCount;

	public static void main(String[] args) {
		int[] array = { 9, 8, 7, 6, 1, 3, 2, 4, 5 };
		// selectSort(array);
		// buddleSort(array);
		insertSort(array);
		printArray(array);
	}

	private static void insertSort(int[] array) {
		int size = array.length;
		for (int i = 1; i < size; i++) {
			int temp = array[i];
			int j;
			for (j = i; j > 0 && array[j - 1] > temp; j--) {
				compareCount++;
				array[j] = array[j - 1];
			}
			array[j] = temp;
		}

	}

	private static void buddleSort(int[] array) {
		int size = array.length;
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1 - i; j++) {
				compareCount++;
				if (array[j] > array[j + 1]) {
					swapCount++;
					swap(j, j + 1, array);
				}
			}
		}
	}

	private static void selectSort(int[] array) {
		int size = array.length;
		for (int i = 0; i < size - 1; i++) {
			int index = i;
			for (int j = i + 1; j < size; j++) {
				compareCount++;
				if (array[j] < array[index]) {
					index = j;
				}
			}
			if (i != index) {
				swapCount++;
				swap(i, index, array);
			}
		}
	}

	private static void swap(int i, int j, int[] array) {
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}

	private static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.print("\n Compare: " + compareCount + "\t Swap: "
				+ swapCount);
	}
}
