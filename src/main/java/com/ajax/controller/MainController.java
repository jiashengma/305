package com.ajax.controller;

import com.ajax.model.Airport;
import com.ajax.model.Constants;
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

import com.ajax.model.Customer;
import com.ajax.model.Employee;
import com.ajax.model.Flight;
import com.ajax.model.FlightClass;
import com.ajax.model.State;
import com.ajax.service.FlightReservationService;
import com.ajax.service.FlightService;
import com.ajax.service.PersonEntitiesService;
import com.ajax.service.RegitrationService;
import com.ajax.service.ReturnValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;



@Controller
@ControllerAdvice
public class MainController {

    State state;

    @Autowired
    private RegitrationService regitrationService;

    @Autowired
    private PersonEntitiesService personEntitiesService;

    @Autowired
    private FlightReservationService flightReservationService;

    @Autowired
    private ServletContext context;

    @Autowired
    private FlightService flightService;

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

    @ModelAttribute
    public void init(HttpServletRequest request) {
        // load airports for flight search
        if (context.getAttribute("s_airports") == null) {
            List<Airport> airports = flightReservationService.getAirports();
            context.setAttribute("s_airports", airports);
            context.setAttribute("classes", FlightClass.values());
        }

        // load states for registration
        if (context.getAttribute("states") == null) {
            context.setAttribute("states", State.values());
        }

        // load customer representatives for reservation
        if (context.getAttribute("customerRepresentatives") == null) {
            context.setAttribute("customerRepresentatives", personEntitiesService.getAllCustomerRepresentatives());
        }

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    private ModelAndView registration() {
        ModelAndView mv = new ModelAndView("registration");
        return mv;
    }

    /**
     * PRG - G
     *
     * @return //@see handleRegistration
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    private ModelAndView redirectRegistration() {
        return new ModelAndView("index");
    }

    /**
     * Post,Redirect,Get(PRG) - P,R -- handles user registration form
     *
     * @param customer
     * @param result
     * @param formValues
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView handleRegistration(
            @Valid @ModelAttribute("customer") Customer customer,
            BindingResult result,
            @RequestParam Map<String, String> formValues,
            HttpServletRequest request,
            final RedirectAttributes redirectAttributes) {

        // redirect to prevent double submission when refreshing page
        ModelAndView modelAndView = new ModelAndView("redirect:register");

        if (result.hasErrors()) {
            //redirectAttributes.addFlashAttribute("msg", "Error in registration form");
            modelAndView.setViewName("/registration");
        } else {
            // set state (the enum type)
            customer.getAddress().setState(formValues.get("state"));

            // add user to database
            if (regitrationService.registerCustomer(customer, formValues) == ReturnValue.ERROR) {
                redirectAttributes.addFlashAttribute("msg", "Error in registering: failed to add user to database");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Registration success");
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/account-setting", method = RequestMethod.GET)
    public ModelAndView manageAccountSettings() {
        ModelAndView mv = new ModelAndView("account-setting");
        return mv;
    }

    @RequestMapping(value = "/best-seller", method = RequestMethod.GET)
    public ModelAndView bestSeller() {
        ModelAndView mv = new ModelAndView("best-seller");

        if (context.getAttribute(Constants.BEST_SELLER) == null) {
            context.setAttribute(Constants.BEST_SELLER, flightService.getBestSeller());
        }
        return mv;
    }

    /**
     * Customers get their own flight suggestion
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/flight-suggestion", method = RequestMethod.GET)
    public ModelAndView flightSuggestion(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("flight-suggestion");
        Customer customer = (Customer) (request.getSession().getAttribute(Constants.PERSON));
        mv.addObject(Constants.FLIGHT_SUGGESTIONS, flightService.getFlightSuggestion(customer.getAccNum()));
        return mv;
    }

    /**
     * Customer representatives get list of flight suggestions for customers
     *
     * @param request
     * @param requesterId
     * @return
     */
    @RequestMapping(value = "/flight-suggestion-for-customer", method = RequestMethod.GET)
    public ModelAndView flightSuggestion(HttpServletRequest request,
            @RequestParam("requester") int requesterId) {
        ModelAndView mv = new ModelAndView("flight-suggestion");
        Employee employee = null;
        try {
            employee = (Employee) (request.getSession().getAttribute(Constants.PERSON));
        } catch (NullPointerException npe) {
            return new ModelAndView("index");
        }
        if (employee == null) {
            return new ModelAndView("index");
        }

        Map<Integer, List<Flight>> flightSuggestions = new HashMap<>();
        List<Customer> customers = regitrationService.getAllCustomersByRepSSN(employee.getSsn());
        
        for (Customer c : customers) {
            flightSuggestions.put(c.getAccNum(), (List<Flight>)flightService.getFlightSuggestion(c.getAccNum()));
        }

        mv.addObject(Constants.FLIGHT_SUGGESTIONS, flightSuggestions);
        mv.addObject("customers", customers);
        // add this object to jsp to indicate control is coming from rep
        mv.addObject("requester", requesterId);
        return mv;
    }

    @RequestMapping(value = "/mailing-list", method = RequestMethod.GET)
    public ModelAndView mailingList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("mailing-list");
        Employee employee = (Employee) (request.getSession().getAttribute(Constants.PERSON));
        List<Customer> customers = personEntitiesService.getAllCustomersByRepId(employee.getSsn());
        mv.addObject(Constants.CUSTOMERS, customers);
        return mv;
    }

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public ModelAndView help() {
        return new ModelAndView("help");
    }

}
