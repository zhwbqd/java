package arithemtic.sort;
public class BucketSortTest {

    public static int count = 0;

    public static void main(String[] args) {

        int[] data= new int[] { 5, 3, 6, 2, 1, 9, 4, 4, 4, 8, 7 };

       print(data);

       bucketSort(data, 0, 10);

       print(data);

    }

 

    public static void bucketSort(int[] data, int min, int max) {

        // 缓存数组

        int[] tmp =new int[data.length];

        // buckets用于记录待排序元素的信息

        // buckets数组定义了max-min个桶

        int[]buckets = new int[max - min];

        // 计算每个元素在序列出现的次数

        for (int i= 0; i < data.length; i++) {

           buckets[data[i] - min]++;

        }

        // 计算“落入”各桶内的元素在有序序列中的位置

        for (int i= 1; i < max - min; i++) {

           buckets[i] = buckets[i] + buckets[i - 1];

        }

        // 将data中的元素完全复制到tmp数组中

       System.arraycopy(data, 0, tmp, 0, data.length);

        // 根据buckets数组中的信息将待排序列的各元素放入相应位置

        for (int k= data.length - 1; k >= 0; k--) {

           data[--buckets[tmp[k] - min]] = tmp[k];

        }

    }

 

    public static void print(int[] data) {

        for (int i= 0; i < data.length; i++) {

           System.out.print(data[i] + "\t");

        }

       System.out.println();

    }

 

}