package framework.redis;


import framework.verify.Verifier;
import framework.verify.VerifierMessages;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * Key生成器
 *
 * @author jack.zhang
 * @since 8 /18/14 3:15 PM.
 */
public abstract class KeyGenerator {

    private static final String SPLIT_CHAR = "_";

    private static final String PREFIX = "fds";

    /**
     * Generate redis cache key.
     *
     * @param redisKeyType the redis key type
     * @param word         the word
     * @return the string
     */
    public static String generateCacheKey(RedisKeyType redisKeyType, String word) {
        Verifier.create()
                .isNotNull(redisKeyType, "redisKeyType" + VerifierMessages.POSTFIX_NO_NULLS_ALLOWED)
                .isNotEmpty(word, "key" + VerifierMessages.POSTFIX_EMPTY_NOT_ALLOWED)
                .throwIfError();
        return PREFIX + SPLIT_CHAR + redisKeyType.getType() + SPLIT_CHAR + word;
    }

    public static String buildKey(String... fields){
        Verifier.create()
                .isNotEmpty(Arrays.asList(fields), "fields" + VerifierMessages.POSTFIX_EMPTY_NOT_ALLOWED)
                .throwIfError();

        StringBuilder sb = new StringBuilder();
        for (String field : fields) {
            if (StringUtils.isBlank(field)) {
                continue;
            }
            sb.append(field).append(SPLIT_CHAR);
        }
        return sb.substring(0,sb.lastIndexOf(SPLIT_CHAR));
    }

    public static String buildKey(Dimension dimension, String metaData, String... fields) {
        Verifier.create()
                .isNotNull(dimension, "dimension" + VerifierMessages.POSTFIX_NO_NULLS_ALLOWED)
                .isNotEmpty(Arrays.asList(fields), "fields" + VerifierMessages.POSTFIX_EMPTY_NOT_ALLOWED)
                .isNotEmpty(metaData, "metaData" + VerifierMessages.POSTFIX_EMPTY_NOT_ALLOWED)
                .throwIfError();
        StringBuilder sb = new StringBuilder(dimension.getValue() + SPLIT_CHAR);
        for (String field : fields) {
            sb.append(field).append(SPLIT_CHAR);
        }
        sb.append(metaData);
        return sb.toString();
    }

    public static String buildKey(Dimension dimension, String metaData, Duration duration, String... fields) {
        Verifier.create()
                .isNotNull(dimension, "dimension" + VerifierMessages.POSTFIX_NO_NULLS_ALLOWED)
                .isNotEmpty(Arrays.asList(fields), "fields" + VerifierMessages.POSTFIX_EMPTY_NOT_ALLOWED)
                .isNotEmpty(metaData, "metaData" + VerifierMessages.POSTFIX_EMPTY_NOT_ALLOWED)
                .isNotNull(duration, "duration" + VerifierMessages.POSTFIX_NO_NULLS_ALLOWED)
                .throwIfError();
        StringBuilder sb = new StringBuilder(dimension.getValue() + SPLIT_CHAR);
        for (String field : fields) {
            sb.append(field).append(SPLIT_CHAR);
        }
        sb.append(metaData).append(SPLIT_CHAR).append(duration.);
        return sb.toString();
    }
}

