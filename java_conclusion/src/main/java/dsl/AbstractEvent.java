package dsl;

/**
 * Date: 14-7-4
 * Time: 下午9:44
 *
 * @author jack.zhang
 */
public class AbstractEvent {
    private String name,code;

    public AbstractEvent(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
