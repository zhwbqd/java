package zhwb.study.innerclass.pk;

import zhwb.study.innerclass.ParentClass;

/**
 * Date: 2014/8/14
 * Time: 17:02
 *
 * @author jack.zhang
 */
public class Test {

    public static void main(String[] args) {
        ParentClass parentClass = new ParentClass() {
        };
        System.out.println(parentClass.getName());

        ParentClass parentClass1 = new ParentClass("fuck") {
            public String sayHello() {
                return super.getName() + "hello";
            }

            @Override
            public String getName() {
                return sayHello();
            }

        };

        System.out.println(parentClass1.getName());
        System.out.println(parentClass1.getClass().getDeclaredMethods().length);
    }
}
