/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.adapter.objectAdapter;

public class ElectoricAdapter implements WorldWideElectrioc
{

    private ChinaElectoric chinaEle;

    public ElectoricAdapter(ChinaElectoric chinaEle)
    {
        this.chinaEle = chinaEle;
    }
    public void V110()
    {
        System.out.println("Adapter has a 110 voltage function");

    }

    public void V220()
    {
        chinaEle.defaultV();
    }

}
