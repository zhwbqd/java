package framework.redis;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author jack.zhang
 * @since 2014/8/21
 */
public enum Duration {
    ONE_MIN,
    TEN_MIN,
    FIVE_MIN,
    ONE_DAY,
    THIRTY_SEC,;

    private int seconds;

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
            case ONE_DAY:
                return (int) ((DateUtils.ceiling(new Date(), Calendar.DAY_OF_MONTH).getTime() - System.currentTimeMillis()) / 1000);
        }
        return -1;
    }

    /**
     * 获取用于作为后缀的key, 用于滑动时间窗
     *
     * 例如: 8:45:32, duration=ONE_MIN, return: 32; duration=TEN_MIN, return: 45
     *
     * 也就意味着, 8:45:32的数据如果按5分钟划分粒度, 则数据记录在45这个区间上, 过期时间1分钟. 当前是分钟数为N, 则计算公式为 sum(n-5,n)
     *
     * @return postfix of duration
     */
    public String generateKeyPostfix() {
        Calendar currentTime = Calendar.getInstance();
        switch (this) {
            case TEN_MIN:
            case FIVE_MIN:
                return String.valueOf(currentTime.get(Calendar.MINUTE));
            case ONE_DAY:
                return String.valueOf(currentTime.get(Calendar.HOUR_OF_DAY));
            case ONE_MIN:
            case THIRTY_SEC:
                return String.valueOf(currentTime.get(Calendar.SECOND));
        }
        throw new UnsupportedOperationException("Unsupported type: " + this.toString());
    }

    /**
     * 获取不同duration对应的切分key的过期时间, 用于滑动时间窗
     * @return expire second
     */
    public int getKeyExpire(){
        switch (this) {
            case TEN_MIN:
            case FIVE_MIN:
                return 60;
            case ONE_DAY:
                return 3600;
            case ONE_MIN:
            case THIRTY_SEC:
                return 1;
        }
        throw new UnsupportedOperationException("Unsupported type: " + this.toString());
    }
}
