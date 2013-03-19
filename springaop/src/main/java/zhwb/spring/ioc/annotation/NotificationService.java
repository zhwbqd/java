/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.spring.ioc.annotation;

import org.springframework.stereotype.Service;

@Service
public class NotificationService
{
    private NotificationDataService dataService;

    /**
     * @param dataService
     */
    public NotificationService(final NotificationDataService dataService)
    {
        super();
        this.dataService = dataService;
    }

    public String sayHello()
    {
        return dataService.SayJack() + "Hello";
    }

}
