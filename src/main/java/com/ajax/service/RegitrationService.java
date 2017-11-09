package com.ajax.service;

import com.ajax.dbm.PasswordUtility;
import com.ajax.dbm.PersonEntitiesManager;
import com.ajax.model.Customer;
import com.ajax.model.Person;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author majiasheng
 */
@Service
public class RegitrationService {

    @Autowired
    private PasswordUtility passwordUtility;
    @Autowired
    private PersonEntitiesManager personEntitiesManager;

    /**
     * On customer registration, add info to database. Populates three tables:
     * person, customer and login
     *
     * @param customer
     * @param formValues registration form values
     * @return
     */
    public int addCustomer(Customer customer, Map<String,String> formValues) {
        //TODO: do validations here
        // if (!validateRegistration(customer)) {
        //     return ReturnValue.ERROR;
        // }

        // update users password to a hash for security
        customer.setPassword(passwordUtility.getSecuredPassword(customer.getPassword()));

        return personEntitiesManager.addCustomer(customer);
    }

    private boolean validateRegistration(Customer customer, Map<String,String> formValues) {

        //TODO: validate each field of the customer
        // formValues map has 2 password and email fields, valid them
        
        return false;
    }

}
