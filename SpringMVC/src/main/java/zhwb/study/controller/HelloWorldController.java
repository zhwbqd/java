
package zhwb.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController
{
    @RequestMapping("/login")
    public ModelAndView login()
    {
        String message = "Hello World, Spring 3.0!";
		return new ModelAndView("helloworld", "message", message);
    }
}
