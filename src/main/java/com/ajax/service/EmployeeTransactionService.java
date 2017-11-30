package com.ajax.service;

import com.ajax.persistence.EmployeeTransactionDAO;
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
    
    public List<String> getMailingList() {
        return employeeTransactionDAO.getMailingList();
    }
    
    public List<String> getMailingListByEmpId(String ssn) {
        return employeeTransactionDAO.getMailingListByEmpId(ssn);
    }
}
