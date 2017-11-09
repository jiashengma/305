/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author majiasheng
 */
@Controller
@ControllerAdvice
public class FlightReservationController {
    
    @RequestMapping(value = "/register", method = RequestMethod.GET) 
    public ModelAndView searchFlight(HttpServletRequest request) {
        // TODO: please add view(URL)
        ModelAndView mv = new ModelAndView("");
        
        
        return mv;
    }
}
