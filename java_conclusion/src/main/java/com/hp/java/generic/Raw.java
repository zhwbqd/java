package com.hp.java.generic;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Raw {
    // Uses raw type (List) - fails at runtime! - Page 112
    public static void main(String[] args) {
		List<String> strings = new ArrayList<String>();

        Object[] o = new Long[1];
        o[0] = "I am a String";
        List<Long> l = new ArrayList<Long>();

        unsafeAdd(strings, new Integer(42));
        //		 String s = strings.get(0); // Compiler-generated cast
        
		Set<?> set1 = new HashSet();
		set1.add(null);
		// List<Object> o =new ArrayList<Long>();
		Set set2 = new HashSet();
		rawNumElementsInCommon(set1, set2);
		numElementsInCommon(set1, set2);
		if (set1 instanceof Set) {
			Set<?> s = set1;
		}
    }

    private static void unsafeAdd(List list, Object o) {
        list.add(o);
    }

    // Use of raw type for unknown element type - don't do this! - Page 113
    static int rawNumElementsInCommon(Set s1, Set s2) {
        int result = 0;
        for (Object o1 : s1)
            if (s2.contains(o1))
                result++;
        return result;
    }

    // Unbounded wildcard type - typesafe and flexible - Page 113
    static int numElementsInCommon(Set<?> s1, Set<?> s2) {
        int result = 0;
        for (Object o1 : s1)
            if (s2.contains(o1))
                result++;
        return result;
    }
}

