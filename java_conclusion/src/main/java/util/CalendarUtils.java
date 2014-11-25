package util;

import java.util.Calendar;
import java.util.Date;

/**
 * Date: 14/11/25
 * Time: 下午8:44
 *
 * @author jack.zhang
 */
public abstract class CalendarUtils {

    /**
     * 判断传入的时间的小时粒度是否在判断的最小及最大区间范围(前闭后开)
     * 例如: 判断传入时间是否是在0-6点之间, min=0 max=6
     *
     * @param date the date
     * @param min  the min
     * @param max  the max
     * @return the boolean
     */
    public static boolean isHourBetween(Date date, int min, int max) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return (hour >= min && hour < max);
    }


    public static void main(String[] args) {
        System.out.println(isHourBetween(new Date(), 0, 22));
        System.out.println(isHourBetween(new Date(), 21, 22));
        System.out.println(isHourBetween(new Date(), 0, 21));
    }
}
