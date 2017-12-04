package com.ajax.controller;

import com.ajax.model.Auction;
import com.ajax.model.Customer;
import com.ajax.model.Flight;
import com.ajax.model.Constants;
import com.ajax.service.AuctionService;
import com.ajax.service.ReturnValue;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            @ModelAttribute("flight") Flight flight,
            HttpServletRequest request
    //            @RequestParam Map<String,String> requestParams
    ) {

//        String airline = requestParams.get("airline");
//        int flightNo = Integer.parseInt(requestParams.get("flightNo"));
//        double hiddenFare = Double.parseDouble(requestParams.get("hiddenFare"));
//        
//        Flight flight = new Flight();
//        flight.setAirline(airline);
//        flight.setFlightNo(flightNo);
//        flight.setHiddenFare(hiddenFare);
//        
        System.out.println(flight.getAirline() + " " + flight.getFlightNo());

        ModelAndView mv = new ModelAndView("auction");

        mv.addObject(Constants.FLIGHT, flight);

        return mv;
    }

    @RequestMapping(value = "/bid", method = RequestMethod.POST)
    public ModelAndView handleBid(
            @ModelAttribute("auction") Auction auction,
            BindingResult result,
            @RequestParam("hiddenFare") double hiddenFare,
            HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("auctionFail");

        // try to bid
        // int bidStatus = auctionService.reserveFlightFromAuction(auction, hiddenFare);
        if (result.hasErrors()) {
            System.out.println("\n\nerr!!");
        }

        // save auction 
        if (!auctionService.saveAuction(auction)) {
            mv.addObject(Constants.MSG_ATTRIBUTE, "Error Occurred while trying to save auction");
            return mv;
        }

        if (auction.getNYOP() >= hiddenFare) {
            mv.setViewName("selectRep");
            // TODO: add neccessary info to reserve a flight
            mv.addObject(Constants.MSG_ATTRIBUTE, "Bid success");

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
