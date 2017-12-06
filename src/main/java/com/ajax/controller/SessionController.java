package com.ajax.controller;

import com.ajax.model.Person;
import com.ajax.model.Constants;
import com.ajax.service.LoginService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class SessionController {
    
    @Autowired
    private LoginService loginService;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView redirectLogin() {
        return new ModelAndView("index");
    }

    //TODO: may need to have 3 login handlers, customer, admin(manager,representative)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView handleLogin(@RequestParam Map<String, String> requestParams,
            HttpServletRequest request, final RedirectAttributes redirectAttributes) {
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

        if (person == null)   //TODO: show an popup to indicate username and password mismatch
            redirectAttributes.addFlashAttribute("msg", "Username and password do not match");
        else    // add user to session
            request.getSession().setAttribute(Constants.PERSON, person);
        return modelAndView;
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView handleLogout(HttpServletRequest request) {
        request.getSession(false).invalidate(); // destroy session
        return new ModelAndView("index");
    }

}
