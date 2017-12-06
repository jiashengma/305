package com.ajax.controller;

import com.ajax.model.Auction;
import com.ajax.model.Customer;
import com.ajax.model.Flight;
import com.ajax.model.Constants;
import com.ajax.service.AuctionService;
import java.util.List;
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

/**
 *
 * @author majiasheng
 */
@Controller
@ControllerAdvice
public class AuctionController {

    @Autowired
    AuctionService auctionService;

    @RequestMapping(value = "/auction", method = RequestMethod.POST)
//    @RequestMapping(value = "/auction", method = RequestMethod.GET)
    public ModelAndView prepareAuction(
            //            @ModelAttribute("flight") Flight flight,
            HttpServletRequest request,
            //            @RequestParam Map<String,String> requestParams
            @RequestParam(Constants.INDEX_OF_FLIGHT) int indexOfFlight
    ) {

//        String airline = requestParams.get("airline");
//        int flightNo = Integer.parseInt(requestParams.get("flightNo"));
//        double hiddenFare = Double.parseDouble(requestParams.get("hiddenFare"));
//        
//        Flight flight = new Flight();
//        flight.setAirline(airline);
//        flight.setFlightNo(flightNo);
//        flight.setHiddenFare(hiddenFare);
//        System.out.println(flight.getAirline() + " " + flight.getFlightNo());
        ModelAndView mv = new ModelAndView("auction");
        request.getSession().setAttribute(Constants.INDEX_OF_FLIGHT, indexOfFlight);

//        mv.addObject(Constants.FLIGHT, flight);
        //mv.addObject(Constants.INDEX_OF_FLIGHT, indexOfFlight);

        return mv;
    }

    @RequestMapping(value = "/bid", method = RequestMethod.POST)
    public ModelAndView handleBid(
            @ModelAttribute(Constants.AUCTION) Auction auction,
            BindingResult result,
            //            @RequestParam("hiddenFare") double hiddenFare,
//            @RequestParam(Constants.INDEX_OF_FLIGHT) int indexOfFlight,
            HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("auctionFail");

        if (result.hasErrors()) {
            System.out.println("\n\nerr!!");
        }
        
        // save auction 
        if (!auctionService.saveAuction(auction)) {
            mv.addObject(Constants.MSG_ATTRIBUTE, "<p style=\"color:red\">Error Occurred while trying to save auction</p>");
            return mv;
        }

        int indexOfFlight = (Integer)request.getSession().getAttribute(Constants.INDEX_OF_FLIGHT);
        Flight selectedFlight = ((List<Flight>)(request.getSession().getAttribute(Constants.FLIGHT_SEARCH_RESULT))).get(indexOfFlight);
        double hiddenFare = selectedFlight.getHiddenFare();
        
        if (auction.getNYOP() >= hiddenFare) {
            mv.setViewName("selectRep");
            mv.addObject(Constants.MSG_ATTRIBUTE, "<p style=\"color:green\">Bid success</p>");
            // mv.addObject(Constants.AUCTION, auction);
            // add auction "object" as an indication of coming from auction
            mv.addObject(Constants.AUCTION, 0);
            
        } else {
            mv.addObject(Constants.MSG_ATTRIBUTE, "Low Bid.. Please try again :)");
        }

        return mv;
    }

    @RequestMapping(value = "/auction-history", method = RequestMethod.GET)
    public ModelAndView showAuctionHistory(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("auction-history");
        Customer customer = (Customer) (request.getSession().getAttribute(Constants.PERSON));
        List<Auction> auctions = auctionService.getAllAuctionHistory(customer.getAccNum());
        if (auctions == null || auctions.size() == 0) {
            mv.setViewName(request.getRequestURI());
            return mv;
        }
        mv.addObject("auctions", auctions);

        return mv;
    }

    @RequestMapping(value = "/auctionFail", method = RequestMethod.GET)
    public ModelAndView goToAuctionFail() {
        return new ModelAndView("auctionFail");
    }

}
