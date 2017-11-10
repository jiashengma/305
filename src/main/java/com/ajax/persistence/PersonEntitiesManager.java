package com.ajax.persistence;

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
import com.ajax.model.Person;
import com.ajax.service.ReturnValue;
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
        int ret = ReturnValue.ERROR;
        
        String query = "INSERT INTO "
                + DBConstants.CUSTOMER_TABLE
                + " VALUES (?,?,?,?,?,?,?,?)";
        
        try {
            /* try to add login information and add person record for 
             * registering customer
             * return immediately if error occurred
             */
            if (addLoginForCustomer(customer) != ReturnValue.ERROR && addPerson(customer) != ReturnValue.ERROR) {
                PreparedStatement stmt = conn.prepareStatement(query);
                conn.prepareStatement(query);
                
                //TODO: add values to customer table 
                ret = stmt.executeUpdate();
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //TODO: rollback if error or set the commit point to here, disable auto commit
                
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonEntitiesManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;
    }

    /**
     * 
     * @param person
     * @return 
     */
    public int addPerson(Person person) {
        int ret = ReturnValue.ERROR;
        try {
            Connection conn = MySQLConnection.connect();
            String query = "INSERT INTO "
                    + DBConstants.PERSON_TABLE
                    + " VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, person.getId());
            stmt.setString(2, person.getFirstName());
            stmt.setString(3, person.getLastName());
            stmt.setString(4, person.getAddress().getStreet());
            stmt.setString(5, person.getAddress().getCity());
            stmt.setString(6, person.getAddress().getState().name());
            stmt.setInt(7, person.getAddress().getZipCode());
            stmt.setLong(8, person.getPhone());

            ret = stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PersonEntitiesManager.class.getName()).log(Level.SEVERE, null, ex);
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
        String query = "INSERT INTO " 
                + DBConstants.LOGIN_TABLE 
                + " VALUES (?,?,?)";
        int ret = ReturnValue.ERROR;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customer.getId());
            stmt.setString(2, customer.getUserName());
            stmt.setString(3, customer.getPassword());
            ret = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PersonEntitiesManager.class.getName()).log(Level.SEVERE, null, ex);
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
     *
     * @param username
     * @param password
     * @return
     */
    public Person login(String username, String password) {
        Person person = null;
        Connection conn = MySQLConnection.connect();
        String query = "SELECT * FROM " + DBConstants.LOGIN_TABLE 
                + " WHERE " 
                + DBConstants.USERNAME_FIELD 
                +" = ? AND password = ? LIMIT 1;";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            // turn password into a hash before querying
            stmt.setString(2, passwordUtility.getSecuredPassword(password));

            ResultSet rs = stmt.executeQuery();
            // conn.commit(); 

            // construct user
            while (rs.next()) {
                // FIXME
                 person = new Person();
                // person.setId(rs.getInt("id"));

                // limit 1
                break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

}
