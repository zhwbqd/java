package zhwb.proxy;

import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IService real = new ServiceImpl();
		DynamicProxyService service = new DynamicProxyService(real);
		IService proxy = (IService) service.bindProxy();
		List<String> list = proxy.doBusiness();
		System.out.println(list);
	}

}
