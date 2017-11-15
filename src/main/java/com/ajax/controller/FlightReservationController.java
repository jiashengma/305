package com.ajax.controller;

import com.ajax.model.Flight;
import com.ajax.model.FlightSearchForm;
import com.ajax.model.Person;
import com.ajax.persistence.Constants;
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

import java.util.Enumeration;
import java.util.Map;

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
        if (result.hasErrors()) {
            //TODO: display message to user instead
            System.out.println("Flight search form has error");
            return new ModelAndView("index");
        }

        ArrayList<Flight> flights = (ArrayList<Flight>) flightReservationService.searchFlight(flightSearchForm);

        // TEST
        // ArrayList<Flight> flights = new ArrayList<Flight>();
        // flights.add(new Flight());
        
        // add a list of flights as the search result for the view/jsp to render
        mv.addObject("flightSearchResult", flights);

        return mv;
    }

    @RequestMapping(value = "/bookflight", method = RequestMethod.GET)
    public ModelAndView handleBookFlight(@RequestParam Map<String, String> requestParams) {

        ModelAndView mv = new ModelAndView();

        //TODO: pass flight to be booked to bookFlight()
        if (flightReservationService.bookFlight(null)) {
            //TODO: booking flight succeeded, set view 
            // make sure to not overbook a flight

            mv.setView(null);
        } else {
            /* TODO: set view or display message
                  maybe set up a few return codes from the bookFlight() method
                  1=success, 0=fail_due_to_full_flight, -1=error of some sort
            */
            // mv.setView("index");
            // mv.addObject("msg", "Failed to book flight");
        }

        return mv;
    }
    
    @RequestMapping(value = "/auction", method = RequestMethod.GET)
    public ModelAndView prepareAuction() {
        return new ModelAndView("auction");
    }
    @RequestMapping(value = "/prepareAuction", method = RequestMethod.POST)
    public ModelAndView prepareAuction(@RequestParam Map<String, String> requestParams,
            HttpServletRequest request) {
        
        /* person should not be null here, 
           it should be checked before coming into here
           But, check anyways
        */
        Person person = (Person)request.getSession().getAttribute(Constants.PERSON);
        if(person==null) {
            //TODO: test this and test request.getRequestURL()
            return new ModelAndView("redirect:"+request.getRequestURI());
        }
        
        ModelAndView mv = new ModelAndView("redirect:auction");
        String airline = requestParams.get("airline");
        String flightNo = requestParams.get("flightNo");
        double hiddenFare = Double.parseDouble(requestParams.get("hiddenFare"));
        mv.addObject("airline", airline);
        mv.addObject("flightNo", flightNo);
        mv.addObject("hiddenFare", hiddenFare);

        return mv;
    }
    
    @RequestMapping(value = "/bid", method = RequestMethod.POST)
    public ModelAndView handleBid(@RequestParam Map<String, String> requestParams) {
        
        boolean status = flightReservationService.handleBid(requestParams);
        
        //TODO:
        return null;
    }

}
