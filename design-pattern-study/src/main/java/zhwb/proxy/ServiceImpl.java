package zhwb.proxy;

import java.util.ArrayList;
import java.util.List;

public class ServiceImpl implements IService {

	public List<String> doBusiness() {
		List<String> array = new ArrayList<String>();
		array.add("A");
		array.add("B");
		return array;
	}

}
