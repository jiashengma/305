/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.controller;

import com.ajax.model.Employee;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author majiasheng
 */
@Controller
public class AdminController {
    
    @RequestMapping(value="/employee-registraion", method=RequestMethod.GET)
    public ModelAndView registerEmployee() {
        return new ModelAndView("employee-registraion");
    }
    
    @RequestMapping(value="/addemployee", method=RequestMethod.GET)
    public ModelAndView addEmployee(
            @Valid @ModelAttribute("employee") Employee employee) {
        
        //TODO: add employee to db
        
        return new ModelAndView("");
    }
}
