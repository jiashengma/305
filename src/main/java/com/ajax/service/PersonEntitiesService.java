package com.ajax.service;

import com.ajax.model.AccessControl;
import com.ajax.model.Customer;
import com.ajax.model.Employee;
import com.ajax.model.Person;
import com.ajax.persistence.PersonEntitiesManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author majiasheng
 */
@Service
public class PersonEntitiesService {
    
    @Autowired
    private PersonEntitiesManager personEntitiesManager;
    
    public int addCustomer(Customer customer) {
        return personEntitiesManager.addCustomer(customer);
    }

    
    public Customer getCustomerById(int id) {
        return personEntitiesManager.getCustomerById(id);
    }

    public Employee getEmployeeById(int id, AccessControl ac) {
       return personEntitiesManager.getEmployeeById(id, ac);
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public Person login(String username, String password) {
        return personEntitiesManager.login(username, password);
    }

    public AccessControl getAccessControl(int id) {
        return personEntitiesManager.getAccessControl(id);
    }

    public List<Employee> getAllCustomerRepresentatives() {
        return personEntitiesManager.getAllCustomerRepresentatives();
    }

    public List<Customer> getAllCustomers() {
        return personEntitiesManager.getAllCustomers();
    }

    public List<Customer> getAllCustomersByRepId(String ssn) {
        return personEntitiesManager.getAllCustomersByRepId(ssn);
    }
    
    public int registerEmployee(Employee customerRepresentative) {
        return personEntitiesManager.registerEmployee(customerRepresentative);
    }

    public int updateCustomer(Customer customer) {
        //NOTE: customer's email will be null, because email WILL NOT be updated
        throw new UnsupportedOperationException("update customer not yet supported");
    }

    public int updateEmployee(Employee employee) {
        throw new UnsupportedOperationException("update employee not yet supported");
    }

    public int deleteCustomer(Customer customer) {
        throw new UnsupportedOperationException("delete customer not yet supported");
    }

    public int deleteEmployee(Employee employee) {
        throw new UnsupportedOperationException("delete employee not yet supported");
    }
    
}
