package com.ajax.controller;

import com.ajax.model.Auction;
import com.ajax.model.Customer;
import com.ajax.model.Person;
import com.ajax.persistence.Constants;
import com.ajax.service.AuctionService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
        Person person = (Person) request.getSession().getAttribute(Constants.PERSON);
        if (person == null) {
            //TODO: test this and test request.getRequestURL()
            return new ModelAndView("redirect:" + request.getRequestURI());
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

    @RequestMapping(value = "/bid", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public int handleBid(@RequestParam Map<String, String> requestParams) {

        int bidderAccNo = Integer.parseInt(requestParams.get("bidderAccNo"));
        double bid = Double.parseDouble(requestParams.get("bid"));
        double hiddenFare = Double.parseDouble(requestParams.get("hiddenFare"));
        String airline = requestParams.get("airline");
        int flightNo = Integer.parseInt(requestParams.get("flightNo"));

        // try to bid
        int bidStatus = auctionService.handleBid(bidderAccNo, bid, hiddenFare, airline, flightNo);

        return bidStatus;
    }
    
    @RequestMapping(value ="/auction-history", method = RequestMethod.GET)
    public ModelAndView showAuctionHistory(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("auction-history");
        //Person person = (Person)(request.getSession().getAttribute(Constants.PERSON));
        // TODO: get person(customer)'s account number 
        // int accNo = 
        //List<Auction> auctions = auctionService.getAllAuctionHistory(accNo);
        List<Auction> auctions = auctionService.getAllAuctionHistory(2);
        if (auctions==null){
            mv.setViewName(request.getRequestURI());
            return mv;
        } 
        mv.addObject("auctions", auctions);
        
        return mv;
    }
}
