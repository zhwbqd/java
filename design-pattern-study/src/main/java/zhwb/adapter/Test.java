/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.adapter;

public class Test
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        ISpecialOrderService order = new CombinationOrderService();
        order.countSell();
        order.countSpecialSell();
    }

}
