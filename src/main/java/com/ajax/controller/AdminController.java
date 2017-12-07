/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.controller;

import com.ajax.model.Address;
import com.ajax.model.Constants;
import com.ajax.model.Customer;
import com.ajax.model.Employee;
import com.ajax.service.PersonEntitiesService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author majiasheng
 */
@Controller
public class AdminController {

    @Autowired
    PersonEntitiesService personEntitiesService;
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
        mv.addObject("customerRepresentatives", cusReps);

        return mv;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ModelAndView showCustomers(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("customers");

        String repssn = ((Employee) (request.getSession().getAttribute(Constants.PERSON))).getSsn();

        List<Customer> customers = regitrationService.getAllCustomersByRepSSN(repssn);
        mv.addObject(Constants.CUSTOMERS, customers);

        return mv;
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public boolean adminDeleteUser(@RequestParam("id") int id, HttpServletRequest request) {
        return personEntitiesService.deleteEmployee(id);
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public boolean adminEditEmployee(
            @ModelAttribute("employee") Employee employee,
            BindingResult r1,
            @ModelAttribute("address") Address address,
            BindingResult r2,
            @RequestParam("state") String state) {
        if (r1.hasErrors() || r2.hasErrors()) {
            System.err.println(r1.toString() + " | " + r2.toString());
            return false;
        }
        address.setState(state);
        employee.setAddress(address);
        
        // updateUser() only use these four fields
        return personEntitiesService.updateEmployee(employee);
    }
    
    @RequestMapping(value = "/employee/delete", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public boolean empDeleteCustomer(@RequestParam("id") int id, HttpServletRequest request) {
        return personEntitiesService.deleteCustomer(id);
    }

    @RequestMapping(value = "/employee/update", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public boolean empEditCustomer(
            @ModelAttribute("employee") Customer customer,
            BindingResult r1,
            @ModelAttribute("address") Address address,
            BindingResult r2,
            @RequestParam("state") String state) {
        if (r1.hasErrors() || r2.hasErrors()) {
            System.err.println(r1.toString() + " | " + r2.toString());
            return false;
        }
        address.setState(state);
        customer.setAddress(address);
        
        return personEntitiesService.updateCustomer(customer);
    }

}
