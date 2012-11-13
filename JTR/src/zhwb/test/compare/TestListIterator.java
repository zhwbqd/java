package zhwb.test.compare;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestListIterator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> l1 = new ArrayList<String>();
		List<String> l2 = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			l1.add("test" + i);
		}
		for (int i = 0; i < 20; i++) {
			l2.add("test" + i);
		}

		for (String string1 : l2) {
			Iterator<String> it = l1.iterator();
			while(it.hasNext()){
				if(it.next().equals(string1)){
					System.out.println("Test");
				}
			}
			}
		}

	}

