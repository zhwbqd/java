/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.javabase.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeepCopyAndLightCopy
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(final String[] args)
    {

        List<Object> source = new ArrayList<Object>();

        source.add(new Object());
        source.add(new Object());

        // light copy???
        List<Object> copy1 = new ArrayList<Object>(source);
        source.add(new Object());
        source.set(0, new Object());
        System.out.println(copy1 + "  " + source);

        // deep copy???
        List<Object> copy2 = new ArrayList<Object>(Arrays.asList(new Object[source.size()]));
        Collections.copy(copy2, source);
        source.add(new Object());
        System.out.println(copy2 + "  " + source);
    }

}
