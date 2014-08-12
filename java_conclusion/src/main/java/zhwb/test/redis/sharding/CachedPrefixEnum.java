package zhwb.test.redis.sharding;

/**
 *
 * !!!!注意!!!!
 *
 * 只能顺序添加不能中间插入, 否则会导致所有key失效
 *
 * @author jack.zhang
 *
 */
public enum CachedPrefixEnum {
    /** 用户喜欢的SKU */
    USER_LIKE_SKU,
    /** 用户喜欢商品的操作队列 */
    GOODS_LIKE_JOB_QUEUE,
    /** 用户喜欢的档期id */
    USER_LIKE_BRAND_ID,
    /** 用户喜欢的商品id */
    USER_LIKE_GOODS_ID,
    /** 加入亲子计划的人数*/
    USER_BABY_USER_COUNT,
    /** 商品被喜欢数目*/
    GOODS_LIKE_SET,
    /*品牌收藏*/
    /** 品牌被喜欢信息*/
    USER_LIKE_BRAND_SN,
    /** 用户喜欢品牌操作队列 */
    BRANDS_LIKE_JOB_QUEUE,
    /** 品牌被喜欢数目*/
    BRANDS_LIKE_COUNT,
    /** 所有的品牌 */
    BRANDS_STORE,
    /** sku被喜欢数目 */
    SKU_LIKE_COUNT,
}
