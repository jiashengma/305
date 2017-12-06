package com.ajax.persistence;

import com.ajax.model.Constants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author majiasheng
 */
@Repository
public class EmployeeTransactionDAO {

    
    public List<Integer> getMailingListByEmpId(String ssn) {
        
        String query = "SELECT DISTINCT " + Constants.ACCOUNTNO_FIELD
                + " FROM " + Constants.RESERVATION_TABLE
                + " WHERE " + Constants.REP_SSN_FIELD + " = ?;";
        
        Connection conn =  MySQLConnection.connect();
        
        List<Integer> customerAccNos = new ArrayList<>();
        
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setString(1,ssn);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
                customerAccNos.add(rs.getInt(1));
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeTransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeTransactionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return customerAccNos;
    }
}
