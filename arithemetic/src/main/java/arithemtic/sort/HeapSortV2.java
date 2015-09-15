package arithemtic.sort;

import java.util.Arrays;

/**
 * @author jack.zhang
 * @since 2015/9/9
 */
public class HeapSortV2 {
    public void sort(int[] A) {
        int n = A.length-1;
        for (int i = n; i >= 2; i--) {
            swap(A, 1, i);
            siftDown(A, i - 1);
        }

        for (int i = 2; i <n ; i++) {
            siftup(A, i);
        }

        for (int i = n; i >=2 ; i--) {
            swap(A, 1, i);
            siftDown(A, i - 1);
        }

    }

    public void siftup(int[] A, int n) {
        //堆的性质: (A.length >= i >= 2 )  A[i/2]<A[i]
        //当[1,n-1]是堆时, 在A[n]中任意放置元素无法形成合理的堆. 此时应该从n到1向上筛选.
        while (true) {
            if (n == 1) {//无父节点则无需向上查找
                break;
            }
            int p = n / 2;
            if (A[p] <= A[n]) {
                break;
            }
            swap(A, p, n);
            n = p;
        }
    }

    public void siftDown(int[] A, int n) {
        int i = 1;
        while (true) {
            int c = 2 * i;
            if (c > n) {
                break;
            }

            if (c + 1 <= n) {
                if (A[c + 1] < A[c]) {
                    c++;
                }
            }
            if (A[i] <= A[c]) {
                break;
            }
            swap(A, c, i);
            i = c;
        }
    }

    private void swap(int[] a, int p, int n) {
        int temp = a[p];
        a[p] = a[n];
        a[n] = temp;
    }

    public static void main(String[] args) {
        int[] a = {4, 3, 2, 1};
        new HeapSortV2().siftup(a, a.length-1);
        System.out.println(Arrays.toString(a));
        new HeapSortV2().sort(a);
        System.out.println(Arrays.toString(a));

    }
}
