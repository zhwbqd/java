/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.adapter.objectAdapter;

public class AdapterTester
{
    public static void main(String[] args)
    {
        ChinaElectoric chinaOne = new ChinaElectoric();
        WorldWideElectrioc goAborad = new ElectoricAdapter(chinaOne);
        goAborad.V110();
        goAborad.V220();
    }
}
