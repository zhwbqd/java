package zhwb.powermock.service;

import java.util.List;

public class OutterService {

	public void printList() {
		InnerService svc = new InnerService();
		List<String> list = svc.innerExecute("Test");
		for (String string : list) {
			System.out.println(string);
		}
	}
}
