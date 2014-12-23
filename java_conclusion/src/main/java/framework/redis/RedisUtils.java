package framework.redis;

/**
 * @author sam.yang
 * @since 8/28/14 3:51 PM.
 */
public class RedisUtils {
    /**
     * 将redis取出来的值转化成对应类型的对象
     *
     * @param value
     * @param valueFieldType
     * @return
     */
    public static Object convertResult(Object value, Class valueFieldType) {
        if (value == null || valueFieldType == null) {
            return null;
        }
        if (valueFieldType.equals(int.class) || valueFieldType.equals(Integer.class)) {
            return Integer.valueOf(value.toString());
        } else if (valueFieldType.equals(long.class) || valueFieldType.equals(Long.class)) {
            return Long.valueOf(value.toString());
        } else if (valueFieldType.equals(String.class)) {
            return value.toString();
        } else if (valueFieldType.equals(Double.class) || valueFieldType.equals(double.class)) {
            return Double.valueOf(value.toString());
        } else if (valueFieldType.equals(float.class) || valueFieldType.equals(Float.class)) {
            return Float.valueOf(value.toString());
        } else if (valueFieldType.equals(short.class) || valueFieldType.equals(Short.class)) {
            return Short.valueOf(value.toString());
        } else if (valueFieldType.equals(Boolean.class) || valueFieldType.equals(boolean.class)) {
            return Boolean.valueOf(value.toString());
        } else if (valueFieldType.equals(Byte.class) || valueFieldType.equals(byte.class)) {
            return Byte.valueOf(value.toString());
        }
        return value.toString();
    }
}
