package com.ajax.persistence;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author majiasheng
 */
@Repository
public class EmployeeTransactionDAO {

    
    public List<String> getMailingList() {
        String query = "SELECT CONCAT(P.FirstName, \" \",P.LastName) "
                + "AS Name, C.Email "
                + "FROM Person P, Customer C "
                + "WHERE P.Id = C.Id;";
        
        
        throw new UnsupportedOperationException("getting mailing list is not yet supported");
    }
    
    public List<String> getMailingListByEmpId(String ssn) {
        
        //TODO: revise query
        String query = "SELECT CONCAT(P.FirstName, \" \",P.LastName) "
                + "AS Name, C.Email "
                + "FROM Person P, Customer C"
                + "WHERE P.Id = C.Id "
                + "AND ;";
        
        
        throw new UnsupportedOperationException("getting mailing list is not yet supported");
    }
}
