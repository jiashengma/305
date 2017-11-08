package com.ajax.dbm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajax.model.Customer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Persistence level Manager: manages person (customer, employees)
 *
 * @author majiasheng
 */
@Service
public class PersonEntitiesManager {

    @Autowired
    private PasswordUtility passwordUtility;

    /**
     * On customer registration, add info to database. Populates three tables:
     * person, customer and login
     *
     * @param customer
     * @return
     */
    public int addCustomer(Customer customer) {
        Connection conn = MySQLConnection.connect();
        String queryInsertToPerson = "INSERT INTO " 
                + DBConstants.PERSON_TABLE 
                + " (?,?,?,?,?,?,?,?)";
        
        String queryInsertToCustomer = "INSERT INTO " 
                + DBConstants.PERSON_TABLE 
                + " (?,?,?,?,?,?,?,?)";
        
        int ret = -1;

        //TODO: populate 3 tables 
        try {

            /* try to add log in information for registering customer
             * return immediately if error happens
             */
            if (addLoginForCustomer(customer) != -1) {
                
                
                PreparedStatement stmt = conn.prepareStatement(queryInsertToPerson);
                stmt.setInt(1, customer.getId());
                stmt.setString(2, customer.getFirstName());
                stmt.setString(3, customer.getLastName());
                stmt.setString(4, customer.getAddress().getStreet());
                stmt.setString(5, customer.getAddress().getCity());
                stmt.setString(6, customer.getAddress().getState().name());
                stmt.setInt(7, customer.getAddress().getZipCode());
                stmt.setInt(8, customer.getPhone());
                //TODO: insert person, move to another method
                stmt.executeUpdate();
                
                conn.prepareStatement(queryInsertToCustomer);

                ret = stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonEntitiesManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;
    }

    /**
     * Adds login account and password information for a customer.
     *
     * @param customer
     * @return
     */
    public int addLoginForCustomer(Customer customer) {
        Connection conn = MySQLConnection.connect();
        String query = "INSERT INTO " + DBConstants.LOGIN_TABLE + "(email, username, password) VALUES (?,?,?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, customer.getEmail());
            stmt.setString(1, customer.getUserName());
            stmt.setString(1, customer.getPassword());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PersonEntitiesManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonEntitiesManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return -1;
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public Customer login(String username, String password) {
        Customer user = null;
        Connection conn = MySQLConnection.connect();
        String query = "SELECT * FROM " + DBConstants.LOGIN_TABLE + " WHERE username = ? AND password = ? LIMIT 1;";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            // turn password into a hash before querying
            stmt.setString(2, passwordUtility.getSecuredPassword(password));

            ResultSet rs = stmt.executeQuery();
            // conn.commit(); // set auto commit

            // construct user
            while (rs.next()) {
                // FIXME
                // user = new Customer(rs.getString("email"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName"));
                // user.setId(rs.getInt("id"));

                // limit 1
                break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

}
