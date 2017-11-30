package com.ajax.model;

import com.ajax.persistence.Constants;
import com.ajax.persistence.MySQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Airport {
    private String name;
    private String shortName;
    private String city;
    private String country;
    private static Map<String, Airport> airportFactory;

	public Airport(String shortName, String name, String city, String country) {
		this.name = name.trim();
		this.shortName = shortName;
		this.city = city;
		this.country = country;
	}

	private static void airportSetUp() {
		airportFactory = new HashMap<>();
		StringBuilder query = new StringBuilder("SELECT * FROM ").append(Constants.AIRPORT_TABLE).append(";");
		try {
			Connection conn = MySQLConnection.connect();
			ResultSet rs = conn.prepareStatement(query.toString()).executeQuery();
			while(rs.next())
				airportFactory.put(rs.getString(Constants.AIRPORT_ID), new Airport(rs.getString(Constants.AIRPORT_ID),
						rs.getString(Constants.AIRPORT_NAME), rs.getString(Constants.AIRPORT_CITY), rs.getString(Constants.AIRPORT_COUNTRY)));
			conn.close();
		} catch (SQLException e) {
			System.out.println("ERROR OBTAINING AIRPORT LIST");
		}
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String City) {
        this.city = City;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String Country) {
        this.country = Country;
    }

	@Override
	public String toString() {
		return "Airport{" +
				"name='" + name + '\'' +
				", shortName='" + shortName + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				'}';
	}

	public static Airport getAirportByID(String id) {
		if (airportFactory == null || airportFactory.get(id) == null)   // maybe airport changed
			airportSetUp();
		return airportFactory.get(id);
	}
}
