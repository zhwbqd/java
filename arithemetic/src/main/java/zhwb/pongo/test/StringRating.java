package zhwb.pongo.test;

/**
 * 我们要给每个字母配一个1-26之间的整数，具体怎么分配由你决定，但不同字母的完美度不同，
 * 而一个字符串的完美度等于它里面所有字母的完美度之和，且不在乎字母大小写，也就是说字母F和f的完美度是一样的。
 * 
 * 现在给定一个字符串，输出它的最大可能的完美度。 例如：dad，你可以将26分配给d，25分配给a，这样整个字符串最大可能的完美度为77。
 */
public class StringRating {
	public static void main(String[] args) {
		System.out.println(perfect("BabCdZ  "));
		System.out.println(perfect("AAA ##"));
	}

	public static int perfect(String s) {
		if (s == null || s.trim().length() == 0) {
			return 0;
		}
		int sum = 0;
		s = s.trim().toLowerCase();
		char[] charArray = s.toCharArray();
		for (char c : charArray) {
			if (c >= 97 && c <= 121) {
				sum += c - 96;
			}

		}
		return sum;
	}
}
