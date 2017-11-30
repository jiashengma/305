package com.ajax.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author majiasheng
 */
public class CustomerRepresentative extends Employee {

    private List<Customer> customers;

    public CustomerRepresentative() {
        super();
        customers = new ArrayList<Customer>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomers(Customer customer) {
        customers.add(customer);
    }

}
