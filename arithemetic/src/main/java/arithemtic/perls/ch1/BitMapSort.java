package arithemtic.perls.ch1;

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;

/**
 * 问题描述, 将大文件中的数字排序后输出, 并去重
 *
 * Date: 15/1/11
 * Time: 下午3:26
 *
 * @author jack.zhang
 */
public class BitMapSort {

    public static void main(String[] args) {
        BitMapSort bitMapSort = new BitMapSort();

        int[] numbers = bitMapSort.generateRandomNumbers(100);
        System.out.println(Arrays.toString(numbers));

        bitMapSort.sortAndPrintLargeArray(numbers);
    }

    public void sortAndPrintLargeArray(int[] array) {
        boolean[] bitmap = new boolean[array.length];
        for (int anArray : array) {
            bitmap[anArray] = true;
        }
        for (int i = 0; i < bitmap.length; i++) {
            if (bitmap[i]) {
                System.out.print(i+",");
            }
        }
    }

    public int[] generateRandomNumbers(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = RandomUtils.nextInt(0, n);
        }
        return result;
    }

}
