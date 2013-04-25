package zhwb.caoliu.register;

import java.util.ArrayList;
import java.util.List;

public class Register {
	private static final String PATTERN = "[a-f0-9*]{16}";
	private static final char PLACE_HOLDER = '*';

	public static void main(String[] args) {
		// List<Integer> list = getIndexs("abc*de*f*");
		// System.out.println(list);
		//
		// List<String> list1 = getPossibleByCountByType(2, Type.CHAR);
		// System.out.println(list1);

		List<String> list2 = getResultFromInput("abc*de*f", Type.MIX);
		int count = 0;
		for (String string : list2) {
			System.out.println(string);
			count++;
		}
		System.out.println(count);
	}

	public static List<String> getResultFromInput(String code, Type type) {
		List<String> str = new ArrayList<String>();
		// List<Integer> indexs = getIndexs(code);
		// char[] arr = code.toCharArray();

		List<String> poss = getPossibleByCountByType(2, type);
		for (String s : poss) {
			String[] array = s.split(",");
			int f = code.indexOf(PLACE_HOLDER);
			int l = code.lastIndexOf(PLACE_HOLDER);
			char[] charArr = code.toCharArray();
			charArr[f] = array[0].charAt(0);
			charArr[l] = array[1].charAt(0);
			str.add(String.valueOf(charArr));
		}
		return str;

	}

	private static List<String> getPossibleByCountByType(int size, Type type) {
		if (size <= 0) {
			return null;
		}
		String[] words = new String[] { "a", "b", "c", "d", "e", "f" };
		int[] numbers = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		List<String> list = new ArrayList<String>();
		switch (type) {
		case CHAR:
			for (String s1 : words) {
				for (String s2 : words) {
					list.add(s1 + "," + s2);
				}
			}
			break;
		case NUMBER:
			for (int n1 : numbers) {
				for (int n2 : numbers) {
					list.add(n1 + "," + n2);
				}
			}
			break;
		case MIX:
			for (String s1 : words) {
				for (int n1 : numbers) {
					list.add(s1 + "," + n1);
				}
			}
			for (int n1 : numbers) {
				for (String s1 : words) {
					list.add(n1 + "," + s1);
				}
			}
			break;
		}

		return list;

	}

	private static List<Integer> getIndexs(String code) {
		char[] array = code.toCharArray();
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			if (PLACE_HOLDER == array[i]) {
				list.add(i);
			}
		}
		return list;
	}
}
