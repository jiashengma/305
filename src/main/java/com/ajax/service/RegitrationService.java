package com.ajax.service;

import com.ajax.dbm.PersonEntitiesManager;
import com.ajax.model.Customer;
import com.ajax.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author majiasheng
 */
@Service
public class RegitrationService {
    
    @Autowired
    private PersonEntitiesManager personEntitiesManager;
    /**
     * On customer registration, add info to database. Populates three tables:
     * person, customer and login
     *
     * @param customer
     * @return
     */
    public int addCustomer(Customer customer) {
        
        return personEntitiesManager.addCustomer(customer);
    }
}
