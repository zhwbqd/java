package zhwb.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyService implements InvocationHandler{

	private Object service;

	public DynamicProxyService(Object service) {
		super();
		this.service = service;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("Before method log");
		Object result = method.invoke(service, args);
		System.out.println("Return Type is:"
				+ result.getClass().getSimpleName());
		System.out.println("Return Value is:" + result.toString());
		System.out.println("End method log");
		return result;
	}

	public Object bindProxy() {
		return Proxy.newProxyInstance(service.getClass().getClassLoader(),
				service.getClass().getInterfaces(), this);
	}
	
}
