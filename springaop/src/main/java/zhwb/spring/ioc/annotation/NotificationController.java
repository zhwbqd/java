/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.spring.ioc.annotation;

import org.springframework.stereotype.Controller;

@Controller
public class NotificationController
{
    private NotificationService businessService;

    private NotificationDataService dataService;
    /**
     * @param businessService
     */
    public NotificationController(final NotificationService businessService)
    {
        super();
        this.businessService = businessService;
    }

    public String sayHelloWorld()
    {
        return businessService.sayHello() + "World" + dataService.SayJack();

    }

    /**
     * @param businessService
     * @param dataService
     */
    public NotificationController(final NotificationService businessService, final NotificationDataService dataService)
    {
        super();
        this.businessService = businessService;
        this.dataService = dataService;
    }

}
