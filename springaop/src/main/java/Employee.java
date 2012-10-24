/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

public class Employee
{
    private String managerName;

    public String getManagerNm()
    {
        return managerName;
    }

    public void setManagerName(Manager manager)
    {
        managerName = manager.getName();

    }

}
