package com.hp.java.method;

// What does this program print? - Page 194

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetList {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<Integer>(); // -3 -2 -1 0 1 2
        List<Integer> list = new ArrayList<Integer>();

        for (int i = -3; i < 3; i++) {
            set.add(i);
            list.add(i);
        }

        for (int i = 0; i < 3; i++) {
            set.remove(i);
            list.remove(Integer.valueOf(i));
        }

        System.out.println(set + " " + list);
    }
}
