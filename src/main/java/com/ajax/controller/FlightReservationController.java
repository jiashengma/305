package com.ajax.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@Controller
@ControllerAdvice
public class FlightReservationController {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchFlight(HttpServletRequest request) {
        // TODO: please add view(URL)
        ModelAndView mv = new ModelAndView("/SearchResult");

        for (Enumeration s = request.getParameterNames(); request.getParameterNames().hasMoreElements();)
	        System.out.println(s.nextElement());

//	    for (Map.Entry : request.getParameterMap())

//	    try {
//		    request.getReader().lines().forEach(System.out::println);
//
//	    } catch (IOException e) {
//		    e.printStackTrace();
//	    }

	    return mv;
    }
}
