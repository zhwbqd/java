/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.adapter;

//类适配器
public class CombinationOrderService extends OrderService implements ISpecialOrderService
{
    public void countSpecialSell()
    {
        System.out.println("This is a special Sell");
    }
}
