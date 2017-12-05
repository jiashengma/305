package com.ajax.controller;

import com.ajax.model.Airport;
import com.ajax.model.Auction;
import com.ajax.model.BookingType;
import com.ajax.model.Constants;
import com.ajax.model.Flight;
import com.ajax.model.FlightClass;
import com.ajax.model.FlightSearchForm;
import com.ajax.model.State;
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
        
        //TODO: pass flight and auction information ? or how else can actual reservation get neccessary info
        
        return mv;
    }
            
    /**
     * Handles flight reservation from buy now
     * @param requestParams
     * @return 
     */
    @RequestMapping(value = "/bookflight", method = RequestMethod.GET)
    public ModelAndView handleBookFlight(@RequestParam Map<String, String> requestParams) {
        ModelAndView mv = new ModelAndView();
        
        //TODO:
        
        return null;
    }
    
    /**
     * Handles flight reservation from auction
     * @param auction
     * @param result
     * @param requestParams
     * @return 
     */
    @RequestMapping(value = "/bookflightViaAuction", method = RequestMethod.POST)
    public ModelAndView handleBookFlightViaAuction(
            @ModelAttribute("auction") Auction auction,
            BindingResult result,
            @RequestParam Map<String, String> requestParams
    ) {
        ModelAndView mv = new ModelAndView();
        
        if (result.hasErrors()) {
            // TODO: binding error
        }
        
        if (flightReservationService.bookFlight(auction)) {
            //TODO: reservation success, set view name 
        } else {
            //TODO: reservation failed , set view name
        }
        
        return mv;
    }
    
}
