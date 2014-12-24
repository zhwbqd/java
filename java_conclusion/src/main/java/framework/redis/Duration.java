package framework.redis;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间粒度, 用于计算过期时间, 生成key 等
 *
 * @author jack.zhang
 * @since 2014/8/21
 */
public enum Duration {
    FIVE_MIN,
    TEN_MIN,
    TWENTY_FOUR_HOUR,//24小时

    /*请注意! 以下为不支持滑动时间窗的时间类型*/

    THIRTY_SEC,
    ONE_MIN,
    ONE_DAY,//自然天
    ;

    public int getSeconds() {
        switch (this) {
            case THIRTY_SEC:
                return 30;
            case FIVE_MIN:
                return 300;
            case ONE_MIN:
                return 60;
            case TEN_MIN:
                return 600;
            case TWENTY_FOUR_HOUR:
                return 86400;
            case ONE_DAY:
                return (int) ((DateUtils.ceiling(new Date(), Calendar.DAY_OF_MONTH).getTime() - System.currentTimeMillis()) / 1000);
        }

        return -1;
    }

    /**
     * 获取用于作为后缀的key, 用于滑动时间窗
     * <p/>
     * 例如: 8:45:32, duration=ONE_MIN, return: 32; duration=TEN_MIN, return: 45
     * <p/>
     * 也就意味着, 8:45:32的数据如果按5分钟划分粒度, 则数据记录在45这个区间上, 过期时间1分钟. 当前是分钟数为N, 则计算公式为 sum(n-5,n)
     *
     * @return postfix of duration
     */
    public String generateKeySuffix() {
        Calendar currentTime = Calendar.getInstance();
        currentTime.set(Calendar.MILLISECOND, 0);
        /*如需增加时间粒度, 请特别注意代码顺序, 使用同一个calendar实例, 时间跨度由小到大*/
        switch (this) {
            case FIVE_MIN:
            case TEN_MIN:
                currentTime.set(Calendar.SECOND, 0);//保留yyyyMMddHHmm
                return String.valueOf(currentTime.getTimeInMillis());
            case TWENTY_FOUR_HOUR:
                currentTime.set(Calendar.MINUTE, 0);//保留yyyyMMddHH
                return String.valueOf(currentTime.getTimeInMillis());
        }
        throw new UnsupportedOperationException("Unsupported type: " + this.toString());
    }

    /**
     * 获取不同duration对应的切分key的过期时间, 用于滑动时间窗
     *
     * @return expire second
     */
    public int getKeyExpire() {
        switch (this) {
            case TEN_MIN:
            case FIVE_MIN:
                return 60;
            case TWENTY_FOUR_HOUR:
                return 3600;
        }
        throw new UnsupportedOperationException("Unsupported type: " + this.toString());
    }

    public List<String> getKeySuffixes() {
        Calendar currentTime = Calendar.getInstance();
        List<String> suffixes = Lists.newArrayList();
        switch (this) {
            case FIVE_MIN:
                currentTime.set(Calendar.MILLISECOND, 0);
                currentTime.set(Calendar.SECOND, 0);
                for (int i = 0; i < 5; i++) {
                    /*先添加当前时间到list, 再减*/
                    suffixes.add(String.valueOf(currentTime.getTimeInMillis()));
                    currentTime.add(Calendar.MINUTE, -1);
                }
                return suffixes;
            case TEN_MIN:
                currentTime.set(Calendar.MILLISECOND, 0);
                currentTime.set(Calendar.SECOND, 0);
                for (int i = 0; i < 10; i++) {
                    suffixes.add(String.valueOf(currentTime.getTimeInMillis()));
                    currentTime.add(Calendar.MINUTE, -1);
                }
                return suffixes;
            case TWENTY_FOUR_HOUR:
                currentTime.set(Calendar.MILLISECOND, 0);
                currentTime.set(Calendar.SECOND, 0);
                currentTime.set(Calendar.MINUTE, 0);
                for (int i = 0; i < 24; i++) {
                    suffixes.add(String.valueOf(currentTime.getTimeInMillis()));
                    currentTime.add(Calendar.HOUR_OF_DAY, -1);
                }
                return suffixes;
        }
        throw new UnsupportedOperationException("Unsupported type: " + this.toString());
    }
}
