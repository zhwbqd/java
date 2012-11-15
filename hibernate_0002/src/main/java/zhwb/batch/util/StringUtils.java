package zhwb.batch.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author <a href="mailto:liu.anxin13@gmail.com">Tony</a>
 */
public class StringUtils {
	
	/**
	 * 空字符串判断, <span style="color:red;">去左右空格后</span>, 如果为空返回 true<br/>
	 * 
	 * @param param
	 * @return boolean
	 */
	public static boolean isNull(CharSequence param) {
		return (param == null || "".equals(param.toString().trim()));
	}
	
	/**
	 * 日期类型以何种格式转换成字符串类型, 异常则返回 "exception"
	 * 
	 * @param date
	 * @param param : 默认格式 "HH:mm:ss"
	 * @return String
	 */
	public static String getStringFromDate(Date date, String param) {
		try {
			param = isNull(param) ? "HH:mm:ss" : param;
			return new SimpleDateFormat(param).format(date);
		} catch (Exception e) {
			return "exception";
		}
	}
	
}
