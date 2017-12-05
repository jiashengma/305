package com.ajax.model;

/**
 *
 * @author majiasheng
 */
public class Constants {

    // request/response/session attributes
    public static final String PERSON = "person";
    public static final String FLIGHT = "flight";
    public static final String CUSTOMER_REPRESENTATIVE = "customerRepresentative";
    public static final String CUSTOMERS = "customers";
    public static final String MSG_ATTRIBUTE = "msg";

    // mysql connection
    public static final String HOSTNAME = "localhost";
    public static final String DBNAME = "ajax305";
    public static final String USERNAME = "ajaxadmin";
    public static final String PASSWORD = "passwordajax305";

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

	// customer
	public static final String CUSTOMER_TABLE = "customer";
    public static final String ACCOUNTNO_FIELD = "AccountNo";
    public static final String CREDITCARDNO_FIELD = "CreditCardNo";
    public static final String EMAIL_FIELD = "Email";
    public static final String RATING_FIELD = "Rating";

    // passenger
    public static final String PASSENGER_TABLE = "passenger";

    // reservation
    public static final String RESERVATION_TABLE = "reservation";
    public static final String RESERVATION_NO_FIELD = "ResrNo";
    public static final String RESERVATION_DATE_FIELD = "ResrDate";
    public static final String BOOKING_FEE_FIELD = "BookingFee";
    public static final String TOTAL_FEE_FIELD = "TotalFare";
    public static final String REP_SSN_FIELD = "RepSSN";

	// reservationpassenger
	public static final String RESERVATION_PASSENGER_TABLE = "reservationpassenger";
	public static final String SEATNO_FIELD = "SeatNo";
	public static final String MEAL_FIELD = "Meal";

	// includes
	public static final String INCLUDES_TABLE = "includes";
	public static final String DEPDATE_FIELD = "DepDate";

    public static final String LOGIN_TABLE = "login";
    public static final String USERNAME_FIELD = "username";
    public static final String PASSWORD_FIELD = "password";

    public static final String AIRPORT_TABLE = "airport";
    public static final String NAME_FIELD = "Name";
    public static final String COUNTRY_FIELD = "Country";

    public static final String FLIGHT_TABLE = "flight";
    public static final String AIRLINEID_FIELD = "AirlineID";
    public static final String FLIGHTNO_FIELD = "FlightNo";
    public static final String NUMSEATS_FIELD = "NoOfSeats";
    public static final String DAYSOPERATING_FIELD = "DaysOperating";
    public static final String MINLENGTHOFSTAY_FIELD = "MinLengthOfStay";
    public static final String MAXLENGTHOFSTAY_FIELD = "MaxLengthOfStay";

	public static final String LEG_TABLE = "leg";
    public static final String LEGNO = "LegNo";
    public static final String DEPATURE_AIRPORT_ID = "DepAirportId";
    public static final String DEPATURE_TIME = "DepTime";
    public static final String ARRIVAL_TIME = "ArrTime";
    public static final String ARRIVAL_AIRPORT_ID = "ArrAirportId";

    public static final int AIRPORT_ID                 = 1;
    public static final int AIRPORT_NAME               = 2;
    public static final int AIRPORT_CITY               = 3;
    public static final int AIRPORT_COUNTRY            = 4;
    public static final String AIRPORT_NAME_FIELD      = "Name";

    public static final String EMPLOYEE_TABLE = "employee";
    public static final String EMPLOYEE_ISMANAGER_FIELD = "IsManager";
    public static final String EMPLOYEE_SSN_FIELD = "SSN";
    public static final String EMPLOYEE_START_DATE_FIELD = "StartDate";
    public static final String EMPLOYEE_HOURLY_RATE_FIELD = "HourlyRate";

    // auction
    public static final String AUCTIONS_TABLE = "auctions";
    public static final String NYOP_FIELD = "NYOP";
    public static final String CLASS_FIELD = "Class";
    public static final String DATE_FIELD = "Date";

    // fare
    public static final String FARE_TABLE = "fare";
    public static final String FARE_TYPE_FIELD = "FareType";
    public static final String FARE_FIELD = "Fare";
    public static final String HIDDEN_FARE_FIELD = "Hidden";
    public static final String REGULAR_FARE_FIELD = "Regular";
}
