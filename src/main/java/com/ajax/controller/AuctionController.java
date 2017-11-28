package com.ajax.controller;

import com.ajax.model.Auction;
import com.ajax.model.Customer;
import com.ajax.model.Flight;
import com.ajax.model.Person;
import com.ajax.model.Constants;
import com.ajax.service.AuctionService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public ModelAndView redirectToAuction() {
        return new ModelAndView("auction");
    }

    //@RequestMapping(value = "/prepareAuction", method = RequestMethod.POST)
    public ModelAndView prepareAuction(@RequestParam Map<String, String> requestParams,
            HttpServletRequest request) {
        /*TODO: on flight search result, 
            put all info of a flight into hidden inputs, 
            and use ModelAttribute to map it here*/

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
    
    @RequestMapping(value = "/prepareAuction", method = RequestMethod.POST)
    public ModelAndView prepareAuction(@ModelAttribute("flight") Flight flight,
            HttpServletRequest request) {
        
        ModelAndView mv = new ModelAndView("redirect:auction");
        //TODO: use redirect attribute if redirection loses this
        mv.addObject(Constants.FLIGHT, flight);

        return mv;
    }

    @RequestMapping(value = "/bid", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public int handleBid(@ModelAttribute("auction") Auction auction, @RequestParam("hiddenFare") double hiddenFare) {

        // try to bid
        int bidStatus = auctionService.handleBid(auction, hiddenFare);

        return bidStatus;
    }
    
    @RequestMapping(value ="/auction-history", method = RequestMethod.GET)
    public ModelAndView showAuctionHistory(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("auction-history");
        Customer customer = (Customer)(request.getSession().getAttribute(Constants.PERSON));
        List<Auction> auctions = auctionService.getAllAuctionHistory(customer.getAccNum());
        if (auctions==null || auctions.size()==0){
            mv.setViewName(request.getRequestURI());
            return mv;
        } 
        mv.addObject("auctions", auctions);
        
        return mv;
    }
}
