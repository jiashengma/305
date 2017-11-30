/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.controller;

import com.ajax.model.Constants;
import com.ajax.model.Customer;
import com.ajax.model.Employee;
import com.ajax.persistence.PersonEntitiesManager;
import com.ajax.service.RegitrationService;
import com.ajax.service.ReturnValue;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
public class AdminController {

    //@Autowired
    //PersonEntitiesManager personEntitiesManager;
    @Autowired
    RegitrationService regitrationService;

    @RequestMapping(value = "/employee-registraion", method = RequestMethod.GET)
    public ModelAndView registerEmployee() {
        return new ModelAndView("employee-registraion");
    }

    @RequestMapping(value = "/register-employee", method = RequestMethod.GET)
    public ModelAndView redirectRegisterEmployee() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/register-employee", method = RequestMethod.POST)
    public ModelAndView registerEmployee(
            @Valid @ModelAttribute("employee") Employee employee,
            BindingResult result,
            @RequestParam Map<String, String> formValues,
            final RedirectAttributes redirectAttributes) {

        // redirect to prevent double submission when refreshing page
        ModelAndView mv = new ModelAndView("redirect:register");

        if (result.hasErrors()) {
            mv.setViewName("employee-registraion");
        } else {

            // set state (the enum type)
            employee.getAddress().setState(formValues.get("state"));

            // add user to database
            if (regitrationService.registerEmployee(employee) == ReturnValue.ERROR) {
                redirectAttributes.addFlashAttribute("msg", "Error in registering: failed to add user to database");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Registration success");
            }
        }

        // add employee to db
        regitrationService.registerEmployee(employee);

        return mv;
    }

    @RequestMapping(value = "/customer-representatives", method = RequestMethod.GET)
    public ModelAndView showEmployees() {

        ModelAndView mv = new ModelAndView("customer-representatives");

        List<Employee> cusReps = regitrationService.getAllCustomerRepresentatives();
        mv.addObject(Constants.CUSTOMER_REPRESENTATIVES, cusReps);

        return mv;
    }
    
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ModelAndView showCustomers(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("customers");

        String repId = ((Employee)(request.getSession().getAttribute(Constants.PERSON))).getSsn();
        
        List<Customer> customers = regitrationService.getAllCustomersByRepId(repId);
        mv.addObject(Constants.CUSTOMERS, customers);

        return mv;
    }

}
