package framework.redis;

/**
 * The enum Redis key type.
 */
public enum RedisKeyType {
    /**
     * The STR.
     */
    STR("k"),
    /**
     * The LIST.
     */
    LIST("l"),
    /**
     * The HASH.
     */
    HASH("h"),
    /**
     * The SET.
     */
    SET("s"),
    /**
     * The ZSET.
     */
    ZSET("z");

    private String type;

    /**
     * Instantiates a new Redis key type.
     *
     * @param type the type
     */
    RedisKeyType(String type) {
        this.type = type;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }
}