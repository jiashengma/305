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
        
        // assign id to person
        customer.setId(Person.count++);
        // assign account number to customer
        customer.setAccNum(Customer.count++);

        return personEntitiesManager.addCustomer(customer);
    }
}
