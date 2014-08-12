package zhwb.test.redis.sharding;


/**
 * 用户生成Redis的key的类, 支持版本变更
 *
 * @author dan.shan
 */
public class CachedPrefix {

    private static final String USER_BABY_WORD = "0";

    private static final String REDIS_PREFIX = "usr:";

    private static byte[] versions = new byte[CachedPrefixEnum.values().length];

    static {
        init();
    }

    private static void init() {
        versions[CachedPrefixEnum.USER_LIKE_SKU.ordinal()] = 1;
        versions[CachedPrefixEnum.USER_LIKE_GOODS_ID.ordinal()] = 1;
        versions[CachedPrefixEnum.USER_LIKE_BRAND_ID.ordinal()] = 1;
        versions[CachedPrefixEnum.GOODS_LIKE_JOB_QUEUE.ordinal()] = 1;
        versions[CachedPrefixEnum.USER_BABY_USER_COUNT.ordinal()] = 1;
        versions[CachedPrefixEnum.GOODS_LIKE_SET.ordinal()] = 1;
        versions[CachedPrefixEnum.USER_LIKE_BRAND_SN.ordinal()] = 1;
        versions[CachedPrefixEnum.BRANDS_LIKE_JOB_QUEUE.ordinal()] = 1;
        versions[CachedPrefixEnum.BRANDS_LIKE_COUNT.ordinal()] = 1;
        versions[CachedPrefixEnum.BRANDS_STORE.ordinal()] = 1;
        versions[CachedPrefixEnum.SKU_LIKE_COUNT.ordinal()] = 1;
    }

    /**
     * generate key for redis
     *
     * @param word
     * @param prefix
     * @return
     */
    public static String generateKey(String word, CachedPrefixEnum prefix) {
        StringBuilder sb = new StringBuilder(REDIS_PREFIX);
        sb.append(prefix.ordinal());
        sb.append(':');
        sb.append(versions[prefix.ordinal()]);
        sb.append(':');
        sb.append(word);
        return sb.toString();
    }

    public static String generateUserLikeBrand(Integer userId) {
        return generateKey(String.valueOf(userId), CachedPrefixEnum.USER_LIKE_BRAND_ID);
    }

    public static String generateBrand(String brandSn) {
        return generateKey(brandSn, CachedPrefixEnum.BRANDS_STORE);
    }

    public static String generateUserBabyUserCount() {
        return generateKey(USER_BABY_WORD, CachedPrefixEnum.USER_BABY_USER_COUNT);
    }

    public static String generateGoodsLikeSet(Integer goodsId) {
        return generateKey(String.valueOf(goodsId), CachedPrefixEnum.GOODS_LIKE_SET);
    }

    public static String generateUserLikeBrandSn(Integer userId) {
        return generateKey(String.valueOf(userId), CachedPrefixEnum.USER_LIKE_SKU);
    }

    public static String generateBrandSnLikeCount(String brandSn) {
        return generateKey(brandSn, CachedPrefixEnum.BRANDS_LIKE_COUNT);
    }

    public static String generateSkuLikeCount(String sku) {
        return generateKey(sku, CachedPrefixEnum.SKU_LIKE_COUNT);
    }
}