package zhwb.study.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * Date: 2014/7/9
 * Time: 15:37
 *
 * @author jack.zhang
 */
public class OgnlTest {
    public static void main(String[] args) throws OgnlException {
        OgnlContext cxt = new OgnlContext();
        Address add1 = new Address("中国", "北京", "大北窑");
        Employee e1 = new Employee("张三", 10000, add1);
        Address add2 = new Address("中国", "北京", "西三旗");
        Employee e2 = new Employee("李四", 12000, add2);
        cxt.put("emp1", e1);
        cxt.put("emp2", e2);
        cxt.setRoot(e1);

        String name = (String) Ognl.getValue("#emp2.name", cxt, cxt.getRoot());
        System.out.println(name);

        double s = (Double)Ognl.getValue("name,#emp2.name,salary", cxt, cxt.getRoot());
        System.out.println(s);

        boolean bool = (Boolean)Ognl.getValue("name in {'张三','李四'}", cxt, cxt.getRoot());
        System.out.println(bool);

        bool = (Boolean)Ognl.getValue("salary > 100", cxt, cxt.getRoot());
        System.out.println(bool);

    }
}
