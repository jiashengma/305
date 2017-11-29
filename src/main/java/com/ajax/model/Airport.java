package com.ajax.model;

public class Airport {
    private String name;
    private String shortName;
    private String city;
    private String country;

    public Airport() {}

	public Airport(String shortName, String name, String city, String country) {
		this.name = name;
		this.shortName = shortName;
		this.city = city;
		this.country = country;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
