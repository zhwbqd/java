package domain;

import java.util.List;

/**
 * @author sam.yang
 * @since 8/11/14 11:13 AM.
 */
public class From {
    private List<Integer> nums;
    private String ips;

    public List getNums() {
        return nums;
    }

    public void setNums(List nums) {
        this.nums = nums;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }
}
