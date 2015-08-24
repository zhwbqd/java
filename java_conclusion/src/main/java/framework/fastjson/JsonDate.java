package framework.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.DateFormatDeserializer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jack.zhang
 * @since 2015/8/10
 */
public class JsonDate {

    private static class A {
        @JSONField(format = "yyyy-MM-dd")
        private Date date;

        public A() {
        }

        public A(Date date) {
            this.date = date;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

    public static class MyDateFormatDeserializer extends DateFormatDeserializer {

        private String myFormat;

        public MyDateFormatDeserializer(String myFormat) {
            super();
            this.myFormat = myFormat;
        }

        @Override
        protected <Date> Date cast(DefaultJSONParser parser, Type clazz, Object fieldName, Object val) {
            if (myFormat == null) {
                return null;
            }
            if (val instanceof String) {
                String strVal = (String) val;
                if (strVal.length() == 0) {
                    return null;
                }

                try {
                    return (Date) new SimpleDateFormat(myFormat).parse((String) val);
                } catch (ParseException e) {
                    throw new JSONException("parse error");
                }
            }
            throw new JSONException("parse error");
        }
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new A(new Date())));

        System.out.println(JSON.parseObject("{\"date\":\"2015-07-15 14:30:11\"}", A.class).getDate());
    }
}
