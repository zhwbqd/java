import java.util.LinkedList;
import java.util.List;

/**
 * Date: 13-11-18
 * Time: 上午10:47
 *
 * @author jack.zhang
 */
public class Test {

    public static void main(String[] args) {
        String ss = "A,B,C";
        List<Enum> garad = new Test().listGrade(Grade.class, ss.split(","));
        for (Enum anEnum : garad) {
            System.out.println(anEnum.toString());
        }
    }

    public List<Enum> listGrade(Class<?> enumClass, String[] strings) {
        List<Enum> list = new LinkedList<Enum>();
        Enum[] enums = (Enum[]) enumClass.getEnumConstants();
        for (Enum anEnum : enums) {
            for (String string : strings) {
                if (anEnum.toString().equals(string)) {
                    list.add(anEnum);
                    break;
                }
            }
        }
        return list;
    }


    public enum Grade {
        A,
        B,
        C,
        D;
    }

}
