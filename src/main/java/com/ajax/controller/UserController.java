package com.ajax.controller;

import com.ajax.model.AccessControl;
import com.ajax.model.Constants;
import com.ajax.model.Customer;
import com.ajax.model.Employee;
import com.ajax.model.Person;
import com.ajax.model.Status;
import com.ajax.service.PersonEntitiesService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class UserController {

    @Autowired
    private PersonEntitiesService personEntitiesService;

    @RequestMapping(value = "/confirmEdit", method = RequestMethod.POST)
    public ModelAndView confirmEdit(
            @ModelAttribute("person") Person person,
            @RequestParam("creditCard") String creditCard,
            HttpServletRequest request,
            final RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();

        int ret = Status.FAILURE;

        AccessControl ac = ((Person) (request.getSession().getAttribute(Constants.PERSON))).getAccessControl();
        person.setAccessControl(ac);
        // update person
        if (ac == AccessControl.CUSTOMER) {
            Customer customer = new Customer(
                    person.getFirstName(),
                    person.getLastName(),
                    person.getPhone(),
                    person.getAddress(),
                    creditCard, null);

            ret = personEntitiesService.updateCustomer(customer);
        } else {
            /*TODO: should have two different methods for updating employee
             admin can update start date and salary
            
             or just one method, for admin only, and disable(do not show)
             account setting in customer rep
             */

            // Employee employee = new Employee(creditCard, null, ret, creditCard, creditCard, creditCard, null)
            // ret = personEntitiesService.updateEmployee((Employee) person);
        }

        if (ret == Status.SUCCESS) {
            mv = new ModelAndView("redirect:/confirmEdit");
            redirectAttributes.addFlashAttribute(Constants.MSG_ATTRIBUTE, "<p style=\"color:green\">Update success</p>");
        } else {
            // send user back to user setting page with error message
            mv.setViewName(request.getRequestURI());
            redirectAttributes.addFlashAttribute(Constants.MSG_ATTRIBUTE, "<p style=\"color:red\">Failed to update</p>");
        }
        return mv;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/confirmEdit", method = RequestMethod.GET)
    public ModelAndView redirectConfirmEdit() {
        ModelAndView mv = new ModelAndView("account-setting");
        return mv;
    }

//    @RequestMapping(value = "/confirmEdit", method = RequestMethod.POST)
//    public ModelAndView confirmEdit(@RequestParam Map<String, String> requestParams,
//            HttpServletRequest request,
//            final RedirectAttributes redirectAttributes) {
//        ModelAndView mv = new ModelAndView();
//        // get user from session
//        User user = (User) request.getSession().getAttribute(SessionConstant.USER_ATTRIBUTE);
//        // update user
//        user.setFirstName(requestParams.get(SessionConstant.FIRSTNAME_REQUEST_PARAM));
//        user.setLastName(requestParams.get(SessionConstant.LASTNAME_REQUEST_PARAM));
//
//        if (userEntityService.updateUser(user)) {
//            mv = new ModelAndView("redirect:/confirmEdit");
//            redirectAttributes.addFlashAttribute(SessionConstant.MSG_ATTRIBUTE, SessionConstant.UPDATE_INFO_SUCCESS_MSG);
//        } else {
//            // send user back to user setting page with error message
//            mv.setViewName(request.getRequestURI());
//            redirectAttributes.addFlashAttribute(SessionConstant.MSG_ATTRIBUTE, SessionConstant.UPDATE_INFO_FAILURE_MSG);
//        }
//        return mv;
//    }
//
//    /**
//     *
//     * @return
//     */
//    @RequestMapping(value = "/confirmEdit", method = RequestMethod.GET)
//    public ModelAndView redirectConfirmEdit() {
//        ModelAndView mv = new ModelAndView("user-setting");
//        return mv;
//    }

}
