package zhwb.test.compare;

import java.util.Arrays;
import java.util.List;

/**
 * Date: 14/12/18
 * Time: 下午8:13
 *
 * @author jack.zhang
 */
public class CompareTwoCollection {
    public static void main(String[] args) {
        List<String> judgements = Arrays.asList("1", "2");

//        List<String> toJudge = Arrays.asList("3", "4");
        List<String> toJudge = Arrays.asList("2");

        System.out.println(judgements.containsAll(toJudge));
    }
}
