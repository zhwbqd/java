package drools.study;


/**
 * Date: 2014/7/17
 * Time: 17:19
 *
 * @author jack.zhang
 */
public class RuleExecResult {

    /**
     * level 从0到10, 0为通过, 10为禁止, 中间1-9为level级别, 越高越危险
     */
    private int level;

    private String reason;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
