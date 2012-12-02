package com.hp.java.enumtest;

// Enum type with constant-specific class bodies and data - Page 153


public enum Operation {
    PLUS("+") {
        double apply(double x, double y) { return x + y; }
    },
    MINUS("-") {
        double apply(double x, double y) { return x - y; }
    },
    TIMES("*") {
        double apply(double x, double y) { return x * y; }
    },
    DIVIDE("/") {
        double apply(double x, double y) { return x / y; }
    };
    private final String symbol;
    Operation(String symbol) { this.symbol = symbol; }
    @Override public String toString() { return symbol; }

    abstract double apply(double x, double y);

    // Test program to perform all operations on given operands
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
		for (Operation op : Operation.values()) {
            System.out.printf("%f %s %f = %f%n",
                              x, op, y, op.apply(x, y));

			System.out.println(Operation.valueOf(op.name()));

		}
    }
}
