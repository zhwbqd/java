package zhwb.powermock.service;

import java.util.ArrayList;
import java.util.List;

public class InnerService {

	public List<String> innerExecute(String str) {
		List<String> list = new ArrayList<String>();
		list.add(str);
		list.add(needMock());
		return list;
	}

	private String needMock() {
		return "Please Mock";

	}
}
