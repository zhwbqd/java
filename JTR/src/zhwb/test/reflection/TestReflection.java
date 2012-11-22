package zhwb.test.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestReflection {

	public static void main(String[] args) throws SecurityException,
			ClassNotFoundException {
		Map<String, Map<Integer, String>> totalMap = new HashMap<String, Map<Integer, String>>();
		Method[] methods = TestMethod.class.getDeclaredMethods();
		for (Method method : methods) {
			Map<Integer, String> map = new HashMap<Integer, String>();

			int paramCount = method.getParameterTypes().length;

			int totalNums = (int) Math.pow(2, paramCount);

			List<String> list = new ArrayList<String>();
			
			Annotation[][] param = method.getParameterAnnotations();
			for (Annotation[] annotations : param) {
				for (Annotation annotation : annotations) {
					ParamName parm = (ParamName) annotation;
					String parmeterName = parm.value();
					list.add(parmeterName);
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("test");
			
			for (int i = 0; i < totalNums; i++) {

			}
			totalMap.put(method.getName(), map);
		}
	}
}
