/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.ioc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBean
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(final String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc-beans.xml");
		// ExecutorService service =
		// (ExecutorService)context.getBean("poolService");
		// service.submit(new Runnable()
		// {
		// public void run()
		// {
		// try
		// {
		// TimeUnit.SECONDS.sleep(3);
		// }
		// catch (InterruptedException e)
		// {
		// e.printStackTrace();
		// }
		// System.out.println("OK");
		//
		// }
		// });

		ExecutorService pool = (ExecutorService) context.getBean("pool");
		pool.submit(new Runnable() {
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("OK");

			}
		});
    }

}
