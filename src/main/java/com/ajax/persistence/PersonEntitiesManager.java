package com.ajax.persistence;

import com.ajax.model.Address;
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

        try {
            String query = "INSERT INTO "
                    + Constants.CUSTOMER_TABLE
                    + " ("
                    + Constants.ID_FIELD + ", "
                    + Constants.ACCOUNTNO_FIELD + ", "
                    + Constants.CREDITCARDNO_FIELD + ", "
                    + Constants.EMAIL_FIELD + ", "
                    + Constants.RATING_FIELD
                    + " ) "
                    + " VALUES (?,?,?,?,?)";

            /* try to add login information and add person record for 
             * registering customer
             * return immediately if error occurred
             */
            if (addLoginForCustomer(customer) != ReturnValue.ERROR && addPerson(customer) != ReturnValue.ERROR) {
                //FIXME: how to rollback the transactions above if anyone of the transactions failed

                PreparedStatement stmt = conn.prepareStatement(query);
                conn.prepareStatement(query);

                stmt.setInt(1, customer.getId());
                stmt.setInt(2, customer.getAccNum());
                stmt.setLong(3, customer.getCreditCard());
                stmt.setString(4, customer.getEmail());
                stmt.setInt(5, customer.getRating());

                ret = stmt.executeUpdate();
                conn.commit();
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
     *
     * @param person
     * @return
     */
    public int addPerson(Person person) {
        int ret = ReturnValue.ERROR;
        Connection conn = MySQLConnection.connect();

        try {
            String query = "INSERT INTO "
                    + Constants.PERSON_TABLE
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
            conn.commit();
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
                + Constants.LOGIN_TABLE
                + " VALUES (?,?,?)";
        int ret = ReturnValue.ERROR;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, customer.getId());
            stmt.setString(2, customer.getUserName());
            stmt.setString(3, customer.getPassword());

            ret = stmt.executeUpdate();
            conn.commit();
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

    public Customer getCustomerById(int id) {
        Customer customer = null;
        Connection conn = MySQLConnection.connect();
        try {

            // get customer from customer and person table
            String query = "SELECT "
                    + " P." + Constants.FIRSTNAME_FILED + ", "
                    + " P." + Constants.LASTNAME_FILED + ", "
                    + " P." + Constants.STREET_FILED + ", "
                    + " P." + Constants.CITY_FILED + ", "
                    + " P." + Constants.STATE_FILED + ", "
                    + " P." + Constants.ZIPCODE_FILED + ", "
                    + " P." + Constants.PHONE_FILED + ", "
                    + " C." + Constants.ACCOUNTNO_FIELD + ", "
                    + " C." + Constants.CREDITCARDNO_FIELD + ", "
                    + " C." + Constants.EMAIL_FIELD + ", "
                    + " C." + Constants.RATING_FIELD
                    + " FROM "
                    + Constants.CUSTOMER_TABLE + " C, "
                    + Constants.PERSON_TABLE + " P"
                    + " WHERE "
                    + " P." + Constants.ID_FIELD
                    + " = ? "
                    + "AND "
                    + " C." + Constants.ID_FIELD
                    + " =  "
                    + " P." + Constants.ID_FIELD
                    + " LIMIT 1;";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            conn.commit();

            while (rs.next()) {
                // get all the fields
                String firstname = rs.getString(Constants.FIRSTNAME_FILED);
                String lastname = rs.getString(Constants.LASTNAME_FILED);
                String street = rs.getString(Constants.STREET_FILED);
                String city = rs.getString(Constants.CITY_FILED);
                String state = rs.getString(Constants.STATE_FILED);
                int zipCode = rs.getInt(Constants.ZIPCODE_FILED);
                long phone = rs.getLong(Constants.PHONE_FILED);
                int acc = rs.getInt(Constants.ACCOUNTNO_FIELD);
                long creditCard = rs.getLong(Constants.CREDITCARDNO_FIELD);
                String email = rs.getString(Constants.EMAIL_FIELD);
                int rating = rs.getInt(Constants.RATING_FIELD);

                System.out.println(
                        "\nfrist name: " + firstname + "\n"
                        + "last name: " + lastname + "\n"
                        + "street: " + street + "\n"
                                + "city: " + city + "\n"
                                + "state: " + state + "\n"
                                + "phone: " + phone + "\n"
                );

                // set all the fields in customer 
                customer = new Customer();
                customer.setFirstName(firstname);
                customer.setLastName(lastname);
                Address address = new Address();
                address.setStreet(street);
                address.setCity(city);
                address.setState(state);
                address.setZipCode(zipCode);
                customer.setAddress(address);
                customer.setPhone(zipCode);
                customer.setCreditCard(creditCard);
                customer.setEmail(email);
                customer.setRating(rating);

                // limit 1 customer
                break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonEntitiesManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonEntitiesManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return customer;
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public Person login(String username, String password) {
        /*TODO: check return type: is Customer more suitable if employees use
         different login portal */
        Person person = null;
        Connection conn = MySQLConnection.connect();
        String query = "SELECT * FROM " + Constants.LOGIN_TABLE
                + " WHERE "
                + Constants.USERNAME_FIELD
                + " = ? AND "
                + Constants.PASSWORD_FIELD
                + " = ? LIMIT 1;";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            // turn password into a hash before querying
            stmt.setString(2, passwordUtility.getSecuredPassword(password));

            ResultSet rs = stmt.executeQuery();
            conn.commit();

            // construct user
            while (rs.next()) {

                int personId = rs.getInt("id");

                // query db to construct person object
                person = getCustomerById(personId);
                // limit 1
                break;
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

        return person;
    }

}
