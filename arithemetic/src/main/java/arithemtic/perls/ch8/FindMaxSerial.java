package arithemtic.perls.ch8;

/**
 * 找出连续最大的整数和
 *
 * Date: 15/1/10
 * Time: 下午10:28
 *
 * @author jack.zhang
 */
public class FindMaxSerial {

    public static void main(String[] args) {
        FindMaxSerial findMaxSerial = new FindMaxSerial();
        System.out.println(findMaxSerial.sum());
        System.out.println(findMaxSerial.fastSum());

    }

    private int[] arrays = {-2,-1};

    public int sum() {
        int max = 0;
        for (int i = 0; i < arrays.length; i++) {
            int temp = 0;
            for (int j = i; j < arrays.length; j++) {
                temp += arrays[j];
                max = Math.max(max, temp);
            }
        }
        return max;
    }

    public int fastSum() {
        int max = 0;
        int maxHere = 0;
        for (int i = 0; i < arrays.length - 1; i++) {
            maxHere = Math.max(maxHere + arrays[i], 0);
            max = Math.max(maxHere, max);
        }
        return max;
    }
}
