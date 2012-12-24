/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.bridge;

public class Test
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        Drawing d1 = new DrawMac();
        Shape shape1 = new Circle(d1);
        shape1.draw();

        Drawing d2 = new DrawWindows();
        Shape shape2 = new Circle(d2);
        shape2.draw();

    }

}
