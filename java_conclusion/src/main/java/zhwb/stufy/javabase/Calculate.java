package zhwb.stufy.javabase;

import java.math.BigInteger;

public class Calculate {
	public static void main(String[] args) {
		BigInteger big = new BigInteger("2");
		BigInteger result = big.pow(100);
		System.out.println(result);
	}
}
