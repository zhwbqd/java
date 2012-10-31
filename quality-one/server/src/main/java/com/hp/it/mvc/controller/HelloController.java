package com.hp.it.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller
{

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		request.setAttribute("hello_1", "Hello, Spring!");
		request.setAttribute("hello_2", "Hello World!");

		return new ModelAndView("hello");
	}

}
