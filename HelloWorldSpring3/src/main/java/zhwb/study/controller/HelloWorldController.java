/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController
{
    public ModelAndView helloWord()
    {
        String message = "Hello World, Spring 3.0!";
        return new ModelAndView("helloworld", "message", message);
    }
}
