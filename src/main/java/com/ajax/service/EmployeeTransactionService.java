package com.ajax.service;

import com.ajax.model.Customer;
import com.ajax.persistence.EmployeeTransactionDAO;
import com.ajax.persistence.PersonEntitiesManager;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author majiasheng
 */
@Service
public class EmployeeTransactionService {
    @Autowired
    EmployeeTransactionDAO employeeTransactionDAO;
    
    @Autowired
    PersonEntitiesManager personEntitiesManager;
    
    public List<Integer> getCustomerAccNosByEmpId(String ssn) {
        return employeeTransactionDAO.getMailingListByEmpId(ssn);
    }
    
    public List<Customer> getCustomersByEmpId(String ssn) {
        return personEntitiesManager.getAllCustomersByRepSSN(ssn);
    }
}
