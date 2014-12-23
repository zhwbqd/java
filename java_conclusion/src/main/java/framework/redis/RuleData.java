package framework.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

/**
 * Date: 2014/7/18
 * Time: 12:09
 *
 * @author jack.zhang
 */
public class RuleData {

    /**
     * 用户ID
     */
    @JSONField(name = "user_id")
    private String uid;

    /**
     * 移动端唯一标识ID
     */
    private String mid;

    /**
     * 基于浏览器的唯一标识ID
     */
    private String cid;

    /**
     * 手机号/电话
     */
    @JSONField(name = "contact_phone")
    private String contactPhone;

    /**
     * IP
     */
    @JSONField(name = "ip")
    private String ip;

    private Map<String, String> extend;

    public static RuleData getInstance(String data) {
        return StringUtils.isBlank(data) ? null : JSON.parseObject(data, RuleData.class);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uid", uid)
                .append("mid", mid)
                .append("cid", cid)
                .append("contactPhone", contactPhone)
                .append("ip", ip)
                .append("extend",extend)
                .toString();
    }

    public Map<String, String> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, String> extend) {
        this.extend = extend;
    }
}
