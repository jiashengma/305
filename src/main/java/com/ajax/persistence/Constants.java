package com.ajax.persistence;

/**
 *
 * @author majiasheng
 */
public class Constants {
    
    public static final String PERSON = "person";

    // mysql connection
    static final String HOSTNAME = "localhost";
    static final String DBNAME = "ajax305";
    static final String USERNAME = "ajaxadmin";
    static final String PASSWORD = "passwordajax305";

    // table and respective column names
    public static final String PERSON_TABLE = "person";
    public static final String ID_FIELD = "Id";
    public static final String FIRSTNAME_FILED = "FirstName";
    public static final String LASTNAME_FILED = "LastName";
    public static final String STREET_FILED = "Street";
    public static final String CITY_FILED = "City";
    public static final String STATE_FILED = "State";
    public static final String ZIPCODE_FILED = "ZipCode";
    public static final String PHONE_FILED = "Phone";

    public static final String CUSTOMER_TABLE = "customer";
    public static final String ACCOUNTNO_FIELD = "AccountNo";
    public static final String CREDITCARDNO_FIELD = "CreditCardNo";
    public static final String EMAIL_FIELD = "Email";
    public static final String RATING_FIELD = "Rating";
    
    public static final String LOGIN_TABLE = "login";
    public static final String USERNAME_FIELD = "username";
    public static final String PASSWORD_FIELD = "password";
    
    public static final String AIRPORT_TABLE = "airport";
    public static final String NAME_FIELD = "Name";
    public static final String COUNTRY_FIELD = "Country";
    
    public static final String FLIGHT_TABLE = "flight";
    public static final String AIRLINEID_FIELD = "AirlineID";
    public static final String FLIGHTNO_FIELD = "FlightNo";
    public static final String NOOFSEATS_FIELD = "NoOfSeats";
    public static final String DAYSOPERATING_FIELD = "DaysOperating";
    public static final String MINLENGTHOFSTAY_FIELD = "MinLengthOfStay";
    public static final String MAXLENGTHOFSTAY_FIELD = "MaxLengthOfStay";
}
