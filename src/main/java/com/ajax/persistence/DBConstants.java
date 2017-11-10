package com.ajax.persistence;

/**
 *
 * @author majiasheng
 */
public class DBConstants {

    // mysql connection
    protected static final String HOSTNAME = "localhost";
    protected static final String DBNAME = "ajax305";
    protected static final String USERNAME = "ajaxadmin";
    protected static final String PASSWORD = "passwordajax305";

    // table and filed names
    protected static final String LOGIN_TABLE = "login";

    protected static final String PERSON_TABLE = "person";
    protected static final String ID_FIELD = "Id";
    protected static final String FIRSTNAME_FILED = "FirstName";
    protected static final String LASTNAME_FILED = "LastName";
    protected static final String STREET_FILED = "Street";
    protected static final String CITY_FILED = "City";
    protected static final String STATE_FILED = "State";
    protected static final String ZIPCODE_FILED = "ZipCode";
    protected static final String PHONE_FILED = "Phone";

    protected static final String CUSTOMER_TABLE = "customer";
    protected static final String ACCOUNTNO_FIELD = "AccountNo";
    protected static final String CREDITCARDNO_FIELD = "CreditCardNo";
    protected static final String EMAIL_FIELD = "Email";
    protected static final String RATING_FIELD = "Rating";
    
    protected static final String USERNAME_FIELD = "username";
    protected static final String PASSWORD_FIELD = "password";
}
