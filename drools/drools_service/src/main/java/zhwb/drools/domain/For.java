package zhwb.drools.domain;

import java.util.List;

/**
 * @author sam.yang
 * @since 8/11/14 11:13 AM.
 */
public class For {
    private int num;
    private List<Integer> nums;
    private String ips;

    public List getNums() {
        return nums;
    }

    public void setNums(List nums) {
        this.nums = nums;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }
}
