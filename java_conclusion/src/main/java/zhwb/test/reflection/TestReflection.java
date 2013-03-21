package zhwb.test.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestReflection {

	public static void main(String[] args) throws SecurityException,
			ClassNotFoundException {
        System.out.println(generateMap());
    }

    private static Map<String, Map<Integer, String>> generateMap()
    {
        Map<String, Map<Integer, String>> totalMap = new HashMap<String, Map<Integer, String>>();
        Method[] methods = TestMethod.class.getDeclaredMethods();
        for (Method method : methods)
        {
            Map<Integer, String> map = new TreeMap<Integer, String>();

            int paramCount = method.getParameterTypes().length;

            int totalNums = (int)Math.pow(2, paramCount);

            List<String> list = new ArrayList<String>();

            Annotation[][] param = method.getParameterAnnotations();
            for (Annotation[] annotations : param)
            {
                for (Annotation annotation : annotations)
                {
                    ParamName parm = (ParamName)annotation;
                    String parmeterName = parm.value();
                    list.add(parmeterName);
                }
            }

            for (int i = 0; i < totalNums; i++)
            {
                StringBuilder sb = new StringBuilder();
                sb.append("test");
                String formatBinary = format(paramCount, Integer.toBinaryString(i));
                sb.append(generateName(formatBinary, list));
                map.put(i, sb.toString());
            }
            totalMap.put(method.getName(), map);
        }
        return totalMap;
    }

    private static String format(int paramCount, String binaryString)
    {
        String temp = new String(binaryString);
        for (int i = binaryString.length(); i < paramCount; i++)
        {
            temp = "0" + temp;
        }
        return temp;
    }

    private static String generateName(String binaryString, List<String> list)
    {
        StringBuilder sb = new StringBuilder();
        char[] binary = binaryString.toCharArray();
        for (int i = 0; i < binary.length; i++)
        {
            sb.append(list.get(i) + binary[i]);
        }
        return sb.toString().replaceAll(String.valueOf(0), "Null").replaceAll(String.valueOf(1), "NotNull");
    }
}
