package com.ajax.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice
public class FlightReservationController {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchFlight(HttpServletRequest request) {
        // TODO: please add view(URL)
        ModelAndView mv = new ModelAndView("");

        
        return mv;
    }
}
