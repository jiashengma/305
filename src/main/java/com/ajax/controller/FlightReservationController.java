package com.ajax.controller;

import com.ajax.model.Auction;
import com.ajax.model.Constants;
import com.ajax.model.Customer;
import com.ajax.model.Flight;
import com.ajax.model.FlightSearchForm;
import com.ajax.model.Reservation;
import com.ajax.service.FlightReservationService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
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
     * @param request
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView handleSearchFlight(
            @ModelAttribute("flightSearchForm") FlightSearchForm flightSearchForm,
            BindingResult result,
            HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("index");
        if (result.hasErrors()) {
            System.err.println("Flight search form has error: " + result.toString());
            mv.addObject(Constants.MSG_ATTRIBUTE, "<p class=\"error\">:( sorry, there is an error in flight search form</p>");
            return mv;
        }

        mv.setViewName("result");

        // add a list of flights as the search result for the view/jsp to render
//        mv.addObject("flightSearchResult", flightReservationService.searchFlight(flightSearchForm));
        request.getSession().setAttribute(
                Constants.FLIGHT_SEARCH_RESULT,
                flightReservationService.searchFlight(flightSearchForm));

        return mv;
    }

    @RequestMapping(value = "/selectRep", method = RequestMethod.POST)
    public ModelAndView selectCustomerRepresentative(
            HttpServletRequest request,
            @RequestParam(Constants.INDEX_OF_FLIGHT) int indexOfFlight) {
        ModelAndView mv = new ModelAndView("selectRep");
        request.getSession().setAttribute(Constants.INDEX_OF_FLIGHT, indexOfFlight);

        return mv;
    }

    /**
     * Handles flight reservation from buy now
     *
     * @param request
     * @param repSSN
     * @return
     */
    @RequestMapping(value = "/bookflight", method = RequestMethod.POST)
    public ModelAndView handleBookFlight(
            HttpServletRequest request,
            @RequestParam("rep") String repSSN
    ) {
        ModelAndView mv = new ModelAndView();
        System.out.println("\n\nrepSSN: " + repSSN);

        //int indexOfFlight = Integer.parseInt((String)request.getSession().getAttribute(Constants.INDEX_OF_FLIGHT));
        int indexOfFlight = (Integer) request.getSession().getAttribute(Constants.INDEX_OF_FLIGHT);
        Flight selectedFlight = ((List<Flight>) (request.getSession().getAttribute(Constants.FLIGHT_SEARCH_RESULT))).get(indexOfFlight);

        if (flightReservationService.bookFlight((Customer) (request.getSession().getAttribute(Constants.PERSON)), repSSN, selectedFlight)) {
            return new ModelAndView("reservation-success");
        }
        return new ModelAndView("reservation-fail");
    }

    /**
     * Handles flight reservation from auction
     *
     * @param auction
     * @param result
     * @param repSSN
     * @param request
     * @return
     */
    @RequestMapping(value = "/bookflightViaAuction", method = RequestMethod.POST)
    public ModelAndView handleBookFlightViaAuction(
            @ModelAttribute("auction") Auction auction,
            BindingResult result,
            @RequestParam("rep") String repSSN,
            HttpServletRequest request
    ) {
        ModelAndView mv = new ModelAndView();

        if (result.hasErrors()) {
            System.err.println(result.toString());
        }
        
        
        //TODO: use the selected flight object? and set the fare to NYOP?
        //if (flightReservationService.bookFlight((Customer) (request.getSession().getAttribute(Constants.PERSON)), repSSN, auction)) {
        
        int indexOfFlight = (Integer) request.getSession().getAttribute(Constants.INDEX_OF_FLIGHT);
        Flight selectedFlight = ((List<Flight>) (request.getSession().getAttribute(Constants.FLIGHT_SEARCH_RESULT))).get(indexOfFlight);
        selectedFlight.setFare(auction.getNYOP());
        
        System.out.println("\n\nauction.getNYOP(): " + auction.getNYOP());
        
        if (flightReservationService.bookFlight((Customer) (request.getSession().getAttribute(Constants.PERSON)), repSSN, selectedFlight)) {
            return new ModelAndView("reservation-success");
        }
        return new ModelAndView("reservation-fail");
    }

    @RequestMapping(value = "/reservation-history", method = RequestMethod.GET)
    public ModelAndView reservationHistory(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("reservation-history");
        Customer c = (Customer) (request.getSession().getAttribute(Constants.PERSON));
        List<Reservation> reservations = flightReservationService.getReservationHistory(c.getAccNum());
        mv.addObject(Constants.RESERVATIONS, reservations);
        return mv;
    }
    
    @RequestMapping(value = "/delete-reservation", method=RequestMethod.GET)
    public ModelAndView deleteReservation(@RequestParam Map<String, String> requestParams){
        ModelAndView mv = new ModelAndView("delete-reservation");
        String resrNo = requestParams.getOrDefault("resrNo", "");
        if (!resrNo.matches("[0-9]+")){
            return mv;
        }
        else {
            flightReservationService.cancelFlight(Integer.parseInt(resrNo));
            mv.addObject("success", "success!");
        }
        return mv;
    }
}
