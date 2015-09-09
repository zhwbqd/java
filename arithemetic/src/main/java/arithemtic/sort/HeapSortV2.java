package arithemtic.sort;

/**
 * @author jack.zhang
 * @since 2015/9/9
 */
public class HeapSortV2 {
    public void sort(int[] A) {

    }

    public void siftup(int[] A) {
        //堆的性质: (A.length >= i >= 2 )  A[i/2]<A[i]
        //当[1,n-1]是堆时, 在A[n]中任意放置元素无法形成合理的堆. 此时应该从n到1向上筛选.
        int n = A.length - 1;
        while (true) {
            if (n == 1) {
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

    private void swap(int[] a, int p, int n) {
        int temp = a[p];
        a[p] = a[n];
        a[n] = temp;
    }

    public static void main(String[] args) {

    }
}
