package framework.redis;

/**
 * @author sam.yang
 * @since 8/20/14 4:52 PM.
 */
public enum Dimension {
    /**
     * The UID.
     */
    UID("uid"),
    /**
     * The CID.
     */
    CID("cid"),
    /**
     * The MID.
     */
    MID("mid"),
    /**
     * The CONTACT_PHONE.
     */
    CONTACT_PHONE("contactPhone"),
    /**
     * The IP.
     */
    IP("ip"),;

    /**
     * The Value.
     */
    private String value;

    /**
     * Instantiates a new Dimension.
     *
     * @param value the value
     */
    Dimension(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }
    public String getFieldValue(RuleData ruleData) {
        switch (this) {
            case UID:
                return ruleData.getUid();
            case CID:
                return ruleData.getCid();
            case MID:
                return ruleData.getMid();
            case CONTACT_PHONE:
                return ruleData.getContactPhone();
            case IP:
                return ruleData.getIp();
        }
        return null;
    }

    /**
     * 返回纬度判空时，对应的固定level信息
     * @return
     */
    public int getFixLevel() {
        switch (this){
            case UID:
                return 50000;
            case CID:
                return 40000;
            case MID:
                return 30000;
            case CONTACT_PHONE:
                return 20000;
            case IP:
                return 10000;
        }
        return 0;
    }

    public String getDimensionValueText(){
        switch (this){
            case UID:
                return "Dimension.UID.getValue()";
            case CID:
                return "Dimension.CID.getValue()";
            case MID:
                return "Dimension.MID.getValue()";
            case CONTACT_PHONE:
                return "Dimension.CONTACT_PHONE.getValue()";
            case IP:
                return "Dimension.IP.getValue()";
        }
        return null;
    }

    public String getRuleDataText(){
        switch (this){
            case UID:
                return "$ruleData.getUid()";
            case CID:
                return "$ruleData.getCid()";
            case MID:
                return "$ruleData.getMid()";
            case CONTACT_PHONE:
                return "$ruleData.getContactPhone()";
            case IP:
                return "$ruleData.getIp()";
        }
        return null;
    }
}
