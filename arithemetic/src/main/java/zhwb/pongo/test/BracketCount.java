package zhwb.pongo.test;

import java.util.Stack;

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
