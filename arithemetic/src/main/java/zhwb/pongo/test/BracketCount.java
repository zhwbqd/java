package zhwb.pongo.test;

import java.util.Stack;

/**
 * 给定只包含括号字符'('和 ')''的字符串，请找出最长的有效括号内子括号的长度。 举几个例子如下：
 * 例如对于"( ()"，最长的有效的括号中的子字符串是"()" ，有效双括号数1个，故它的长度为 2。
 * 再比如对于字符串") () () )"，其中最长的有效的括号中的子字符串是"() ()"，有效双括号数2个，故它的长度为4。
 * 再比如对于"( () () )"，它的长度为6。 换言之，便是有效双括号"()"数的两倍。 给定函数原型int
 * longestValidParentheses(string s)，请完成此函数，实现上述功能。
 */
public class BracketCount {
	private static int longestValidParentheses(String s) {
		int count = 0;
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '(':
				stack.push(ch);
				break;
			case ')':
				if (!stack.isEmpty()) {
					char chx = stack.pop();
					if (chx == '(') {
						count++;
					}
				}
			}
		}
		return count * 2;
	}

	public static void main(String[] args) {
		System.out.println(longestValidParentheses("( ()"));

		System.out.println(longestValidParentheses(") () () )"));

		System.out.println(longestValidParentheses("( () () )"));

		System.out.println(longestValidParentheses("))()( () () )"));

		System.out.println(longestValidParentheses("()()(())((())"));

		System.out.println(longestValidParentheses("(((  ))())"));
	}
}
