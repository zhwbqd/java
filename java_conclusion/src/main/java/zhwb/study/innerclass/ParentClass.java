package zhwb.study.innerclass;

/**
 * Date: 2014/8/14
 * Time: 17:01
 *
 * @author jack.zhang
 */
public class ParentClass {

    private String name;

    protected ParentClass() {
        this.name = "jack";
    }

    protected ParentClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
