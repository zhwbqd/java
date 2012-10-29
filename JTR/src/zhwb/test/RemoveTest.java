/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.test;

import java.util.LinkedList;

public class RemoveTest
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        System.out.println(list);
        list.remove(1);
        list.remove(Integer.valueOf(1));
        System.out.println(list);
    }

}
