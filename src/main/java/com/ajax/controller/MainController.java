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

import com.ajax.model.Customer;
import com.ajax.model.Person;
import com.ajax.model.State;
import com.ajax.persistence.Constants;
import com.ajax.service.LoginService;
import com.ajax.service.RegitrationService;
import com.ajax.service.ReturnValue;

/**
 * Created by majiasheng on 7/14/17.
 */
@Controller
@ControllerAdvice
public class MainController {

    State state;
    @Autowired
    private RegitrationService regitrationService;
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private ModelAndView home() {
        return new ModelAndView("index");
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
     * @return
     * //@see handleRegistration
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

        // DEBUG
        System.out.println("*******");
        customer.getAddress().setState(formValues.get("state"));
        System.out.println("customer:" + customer + "\n\n result tostring: " + result.toString());
        System.out.println("*******");

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg","Error in registration form");
        } else {
                    // update users password to a hash for security
//            customer.setPassword(passwordUtility.getSecuredPassword(customer.getPassword()));

            //TODO: add user to database
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView redirectLogin() {
        return new ModelAndView("index");
    }

    //TODO: may need to have 3 login handlers, customer, admin(manager,representative)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView handleLogin(@RequestParam Map<String, String> requestParams,
            HttpServletRequest request,
            final RedirectAttributes redirectAttributes) {

        // redirect to prevent double submission when refreshing page
        ModelAndView modelAndView = new ModelAndView("redirect:/login");

        /* 
         *	if username and password match,
         *		load user info to session
         *		return to index
         *	else 
         *		show an popup to indicate username and password mismatch
         */
        // get/validate user
        Person person = loginService.login(requestParams.get(Constants.USERNAME_FIELD),
                requestParams.get(Constants.PASSWORD_FIELD));

        if (person == null) {
            //TODO: show an popup to indicate username and password mismatch
            redirectAttributes.addFlashAttribute("msg", "Username and password do not match");
        } else {
            // add user to session            
            request.getSession().setAttribute(Constants.PERSON, person);
        }
        return modelAndView;
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView handleLogout(HttpServletRequest request) {
        // destroy session
        request.getSession(false).invalidate();
        return new ModelAndView("index");
    }

}
