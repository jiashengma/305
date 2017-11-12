/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.controller;

import com.ajax.model.Flight;
import com.ajax.model.FlightSearchForm;
import com.ajax.service.FlightReservationService;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author majiasheng
 */
@Controller
@ControllerAdvice
public class FlightReservationController {

    @Autowired
    FlightReservationService flightReservationService;
    
    
    /**
     *
     * @param binder
     */
    @InitBinder
    public void InitBinder(WebDataBinder binder) {

        // can use binder.setDisallowedFields() to un-bind a property
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        // use a customized date format for "dateAcquired" request param
        //binder.registerCustomEditor(Date.class, "dateAcquired" ,new CustomDateEditor(simpleDateFormat, false));
    }

    /**
     * 
     * @param flightSearchForm
     * @param result
     * @return 
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView handleSearchFlight(@ModelAttribute("flightSearchForm") FlightSearchForm flightSearchForm,  
            BindingResult result) {

        ModelAndView mv = new ModelAndView("result");
        
        if(result.hasErrors()) {
            //TODO: display message to user instead
            System.out.println("Flight search form has error");
            return new ModelAndView("index");
        }
        
        ArrayList<Flight> flights = (ArrayList<Flight>) flightReservationService.searchFlight(flightSearchForm);
        // add a list of flights as the search result for the view/jsp to render
        mv.addObject("result", flights);

        return mv;
    }
    
    @RequestMapping(value = "/bookflight", method = RequestMethod.GET)
    public ModelAndView handleBookFlight(@RequestParam Map<String, String> requestParams) {
        
        
        ModelAndView mv = new ModelAndView();
        
        //TODO: pass flight to be booked to bookFlight()
        if(flightReservationService.bookFlight(null)) {
            //TODO: booking flight succeeded, set view 
            mv.setView(null);
        } else {
            // TODO: set view or display message
            // mv.setView(null);
        }
        
        
        return mv;
    }
    
}
