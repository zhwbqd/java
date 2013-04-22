/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.javabase.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnmodifiableCollection
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(final String[] args)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Jack", "yes");
        map.put("Jack1", "yes");
        map.put("Jack2", "yes");
        Map<String, String> unmap = Collections.unmodifiableMap(map);
        map = Collections.unmodifiableMap(map);
        map.put("Jack3", "yes");
        System.out.println(map);
        unmap.put("Jack2", "no");
        System.out.println(unmap);
    }

}
