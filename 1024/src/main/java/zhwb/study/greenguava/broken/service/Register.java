package zhwb.study.greenguava.broken.service;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class Register {
	private static final String PATTERN = "[a-f0-9*]{16}";
	private static final char PLACE_HOLDER = '*';
	private static final char[] WORDS = new char[] { 'a', 'b', 'c', 'd', 'e',
			'f' };
	private static final char[] NUMBERS = new char[] { '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9' };

	public static void main(final String[] args) {

		List<String> list2 = getResultFromInput("abc*de*f", Type.MIX); // 100+36+120
		int count = 0;
		for (String string : list2) {
			System.out.println(string);
			count++;
		}
		System.out.println(count);
	}

	public static List<String> getResultFromInput(final String code, final Type type) {
		Deque<String> queue = new LinkedList<String>();
		queue.addLast(code);
		switch (type) {
		case CHAR:
			while (queue.peekFirst() != null) {
				String string = queue.peekFirst();
				int place = string.indexOf(PLACE_HOLDER);
				if (place >= 0) {
					queue.pollFirst();
					for (char replace : WORDS) {
						char[] array = string.toCharArray();
						array[place] = replace;
						queue.addLast(String.valueOf(array));
					}
				} else {
					break;
				}
			}
			break;
		case NUMBER:
			while (queue.peekFirst() != null) {
				String string = queue.peekFirst();
				int place = string.indexOf(PLACE_HOLDER);
				if (place >= 0) {
					queue.pollFirst();
					for (char replace : NUMBERS) {
						char[] array = string.toCharArray();
						array[place] = replace;
						queue.addLast(String.valueOf(array));
					}
				} else {
					break;
				}
			}
			break;
		case MIX:
			while (queue.peekFirst() != null) {
				String string = queue.peekFirst();
				int place = string.indexOf(PLACE_HOLDER);
				if (place >= 0) {
					queue.pollFirst();
					for (char replace : NUMBERS) {
						char[] array = string.toCharArray();
						array[place] = replace;
						queue.addLast(String.valueOf(array));
					}
					for (char replace : WORDS) {
						char[] array = string.toCharArray();
						array[place] = replace;
						queue.addLast(String.valueOf(array));
					}
				} else {
					break;
				}
			}
			break;
		}
		return new ArrayList<String>(new LinkedHashSet<String>(queue));
	}

}
