package com.ajax.controller;

import com.ajax.model.*;
import com.ajax.service.FlightReservationService;
import java.util.ArrayList;
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

import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;

@Controller
@ControllerAdvice
public class FlightReservationController {

    @Autowired
    FlightReservationService flightReservationService;
    @Autowired
    ServletContext context;


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
        if (result.hasErrors()) {
            //TODO: display message to user instead
            System.out.println("Flight search form has error");
            return new ModelAndView("index");
        }

        // add a list of flights as the search result for the view/jsp to render
        mv.addObject("flightSearchResult", flightReservationService.searchFlight(flightSearchForm));
        return mv;
    }
    
    
    @RequestMapping(value = "/selectCustomerRepresentative", method = RequestMethod.GET)
    public ModelAndView selectCustomerRepresentative(@RequestParam Map<String, String> requestParams) {
        ModelAndView mv = new ModelAndView("selectRep");
        
        //TODO: get all reps (they are already in servlet context)
        //TODO: pass flight and auction information ? or how else can actual reservation get neccessary info
        
        return mv;
    }
            
    @RequestMapping(value = "/bookflight", method = RequestMethod.POST)
    public ModelAndView handleBookFlight(HttpServletRequest request, @RequestParam Map<String, String> requestParams) {
        ModelAndView mv = new ModelAndView();
	    Customer p = (Customer) request.getSession().getAttribute(Constants.PERSON);

        //TODO: pass flight to be booked to bookFlight()
        //if (flightReservationService.bookFlight(null, BookingType.AUCTION)) {
        //TODO: booking flight succeeded, set view 
        // make sure to not overbook a flight
        //    mv.setView(null);
        // } else {
            /* TODO: set view or display message
         maybe set up a few return codes from the bookFlight() method
         1=success, 0=fail_due_to_full_flight, -1=error of some sort
         */
            // mv.setView("index");
        // mv.addObject("msg", "Failed to book flight");
        //}
         return mv;
    }
}
