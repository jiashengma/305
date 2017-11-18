package com.ajax.persistence;

import com.ajax.model.AccessControl;
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
import com.ajax.model.Employee;
import com.ajax.model.Person;
import com.ajax.service.ReturnValue;
import java.sql.Date;
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
                    + Constants.CREDITCARDNO_FIELD + ", "
                    + Constants.EMAIL_FIELD + ", "
                    + Constants.RATING_FIELD
                    + " ) "
                    + " VALUES (?,?,?,?)";

            /* try to add login information and add person record for 
             * registering customer
             * return immediately if error occurred
             */
            if (addPerson(customer, conn) != ReturnValue.ERROR && addLoginForCustomer(customer, conn) != ReturnValue.ERROR) {

                PreparedStatement stmt = conn.prepareStatement(query);

                stmt.setInt(1, customer.getId());
                stmt.setLong(2, customer.getCreditCard());
                stmt.setString(3, customer.getEmail());
                stmt.setInt(4, customer.getRating());

                ret = stmt.executeUpdate();

                // retrieve auto increment key
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                // set key (id) 
                customer.setAccNum(rs.getInt(1));

                // rollback all three transactions if error occurred
                if (ret == ReturnValue.ERROR) {
                    /* FIXME: is it necessary since we use a same 
                        connection for all these three transactions? */
                    conn.rollback(); 
                } else {
                    conn.commit();
                }
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
    public int addPerson(Person person, Connection conn) {
        int ret = ReturnValue.ERROR;
        try {
            String query = "INSERT INTO "
                    + Constants.PERSON_TABLE
                    + "("
                    + Constants.FIRSTNAME_FILED + ", "
                    + Constants.LASTNAME_FILED + ", "
                    + Constants.STREET_FILED + ", "
                    + Constants.CITY_FILED + ", "
                    + Constants.STATE_FILED + ", "
                    + Constants.ZIPCODE_FILED + ", "
                    + Constants.PHONE_FILED
                    + ")"
                    + " VALUES (?,?,?,?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(query);

            // stmt.setInt(1, person.getId());
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setString(3, person.getAddress().getStreet());
            stmt.setString(4, person.getAddress().getCity());
            stmt.setString(5, person.getAddress().getState().name());
            stmt.setInt(6, person.getAddress().getZipCode());
            stmt.setLong(7, person.getPhone());

            ret = stmt.executeUpdate();
            // retrieve auto increment key
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            // set key (id) 
            person.setId(rs.getInt(1));
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
    public int addLoginForCustomer(Customer customer, Connection conn) {

        int ret = ReturnValue.ERROR;
        try {
            String query = "INSERT INTO "
                    + Constants.LOGIN_TABLE
                    + " VALUES (?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, customer.getId());
            stmt.setString(2, customer.getUserName());
            stmt.setString(3, customer.getPassword());

            ret = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PersonEntitiesManager.class.getName()).log(Level.SEVERE, null, ex);
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

                // set fields in customer
                customer = new Customer(firstname, lastname, phone,
                        new Address(street, city, state, zipCode),
                        creditCard, email);
                customer.setAccessControl(AccessControl.CUSTOMER);
//                customer.setRating(rating);       // set rating later?
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

    public Employee getEmployeeById(int id, AccessControl ac) {
        Employee employee = null;
        Connection conn = MySQLConnection.connect();
        try {
            String query = "SELECT "
                    + " P." + Constants.FIRSTNAME_FILED + ", "
                    + " P." + Constants.LASTNAME_FILED + ", "
                    + " P." + Constants.STREET_FILED + ", "
                    + " P." + Constants.CITY_FILED + ", "
                    + " P." + Constants.STATE_FILED + ", "
                    + " P." + Constants.ZIPCODE_FILED + ", "
                    + " P." + Constants.PHONE_FILED + ", "
                    + " E." + Constants.EMPLOYEE_SSN_FIELD + ", "
                    + " E." + Constants.EMPLOYEE_START_DATE_FIELD + ", "
                    + " E." + Constants.EMPLOYEE_HOURLY_RATE_FIELD
                    + " FROM "
                    + Constants.EMPLOYEE_TABLE + " E, "
                    + Constants.PERSON_TABLE + " P"
                    + " WHERE "
                    + " P." + Constants.ID_FIELD
                    + " = ? "
                    + "AND "
                    + " E." + Constants.ID_FIELD
                    + " =  "
                    + " P." + Constants.ID_FIELD
                    + " LIMIT 1;";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            conn.commit();

            if (rs.next()) {
                String firstname = rs.getString(Constants.FIRSTNAME_FILED);
                String lastname = rs.getString(Constants.LASTNAME_FILED);
                String street = rs.getString(Constants.STREET_FILED);
                String city = rs.getString(Constants.CITY_FILED);
                String state = rs.getString(Constants.STATE_FILED);
                long phone = rs.getLong(Constants.PHONE_FILED);
                int zipCode = rs.getInt(Constants.ZIPCODE_FILED);
                int ssn = rs.getInt(Constants.EMPLOYEE_SSN_FIELD);
                Date startDate = rs.getDate(Constants.EMPLOYEE_START_DATE_FIELD);
                double hourlyRate = rs.getDouble(Constants.EMPLOYEE_HOURLY_RATE_FIELD);
                employee = new Employee(ssn, startDate, hourlyRate, firstname, lastname, phone,
                        new Address(street, city, state, zipCode));

                employee.setAccessControl(ac);
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
        return employee;
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
                AccessControl accessControl = getAccessControl(personId);
                if (accessControl == AccessControl.CUSTOMER) {
                    person = getCustomerById(personId);
                } else {
                    person = getEmployeeById(personId, accessControl);
                }
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

    public AccessControl getAccessControl(int id) {
        Connection conn = MySQLConnection.connect();
        String query = "SELECT * FROM " + Constants.EMPLOYEE_TABLE
                + " WHERE "
                + Constants.ID_FIELD
                + " = ? LIMIT 1";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            conn.commit();
            if (!rs.next()) {
                return AccessControl.CUSTOMER;
            } else if (rs.getBoolean(Constants.EMPLOYEE_ISMANAGER_FIELD)) {
                return AccessControl.MANAGER;
            } else {
                return AccessControl.CUSTOMER_REPRESENTATIVE;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                Logger.getLogger(PersonEntitiesManager.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return AccessControl.ERROR;
    }
}
