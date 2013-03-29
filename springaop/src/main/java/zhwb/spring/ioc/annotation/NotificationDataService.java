/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.spring.ioc.annotation;

import org.springframework.stereotype.Repository;

@Repository
public class NotificationDataService
{
    private String dataSource = "jack";

    public String SayJack()
    {
        return dataSource;
    }
}
