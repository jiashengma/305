package com.ajax.controller;

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

import com.ajax.model.Customer;;
import com.ajax.model.State;
import com.ajax.service.RegitrationService;
import com.ajax.service.ReturnValue;

@Controller
@ControllerAdvice
public class MainController {

    State state;
    @Autowired
    private RegitrationService regitrationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private ModelAndView home() {
        ModelAndView mv = new ModelAndView("index");
//        mv.addObject("airports", flightReservationService.getAirports());
        return mv;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    private ModelAndView registration() {
        ModelAndView mv = new ModelAndView("registration");
        mv.addObject("states", State.values());
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
            @ModelAttribute("customer") Customer customer,
            BindingResult result,
            @RequestParam Map<String, String> formValues,
            HttpServletRequest request,
            final RedirectAttributes redirectAttributes) {

        // redirect to prevent double submission when refreshing page
        ModelAndView modelAndView = new ModelAndView("redirect:register");

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg", "Error in registration form");
        } else {

            // set state (the enum type)
            customer.getAddress().setState(formValues.get("state"));

            // add user to database
            if (regitrationService.addCustomer(customer, formValues) == ReturnValue.ERROR) {
                redirectAttributes.addFlashAttribute("msg", "Error in registering: failed to add user to database");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Registration success");
            }
        }
        return modelAndView;
    }

}
