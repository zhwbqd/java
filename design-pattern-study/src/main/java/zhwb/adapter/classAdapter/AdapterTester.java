/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.adapter.classAdapter;

public class AdapterTester
{
    public static void main(String[] args)
    {
        WorldWideElectrioc goAborad = new ElectoricAdapter();
        goAborad.V110();
        goAborad.V220();
    }
}
