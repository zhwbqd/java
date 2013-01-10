/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.adapter.classAdapter;

public class ElectoricAdapter extends ChinaElectoric implements WorldWideElectrioc
{

    public void V110()
    {
        System.out.println("Adapter has a 110 voltage function");

    }

    public void V220()
    {
        this.defaultV();
    }

}
