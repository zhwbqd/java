package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;

/**
 * Date: 15/1/16
 * Time: 上午12:54
 *
 * @author jack.zhang
 */
public class FastJsonConvert {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new FastJsonConvert()));
    }

    private String ACUN = "test";

    public String getACUN() {
        return ACUN;
    }

    public void setACUN(String ACUN) {
        this.ACUN = ACUN;
    }

    public FastJsonConvert() {
        TypeUtils.compatibleWithJavaBean = true;
    }
}
