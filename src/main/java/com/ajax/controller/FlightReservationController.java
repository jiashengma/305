package com.ajax.controller;

import com.ajax.model.Flight;
import com.ajax.service.FlightReservationService;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;
import java.util.Map;

@Controller
@ControllerAdvice
public class FlightReservationController {
//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public ModelAndView searchFlight(HttpServletRequest request) {
//        // TODO: please add view(URL)
//        ModelAndView mv = new ModelAndView("/SearchResult");
//
//        for (Enumeration s = request.getParameterNames(); request.getParameterNames().hasMoreElements();)
//	        System.out.println(s.nextElement());
//
////	    for (Map.Entry : request.getParameterMap())
//
////	    try {
////		    request.getReader().lines().forEach(System.out::println);
////
////	    } catch (IOException e) {
////		    e.printStackTrace();
////	    }
//
//	    return mv;
//=======

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
     * @param requestParams
     * @return 
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView handleSearchFlight(@RequestParam Map<String, String> requestParams) {

        ModelAndView mv = new ModelAndView("result");

        // TODO: use request params to do query on db
        // FlightReservationService.searchFlight(String src, String dst, Date dep, Date ret)
        ArrayList<Flight> flights = (ArrayList<Flight>) flightReservationService.searchFlight(null, null, null, null);

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
