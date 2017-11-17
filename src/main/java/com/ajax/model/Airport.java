package com.ajax.model;

public class Airport {
    private String name;
    private String shortName;
    private String City;
    private String Country;

    public Airport() {}

	public Airport(String shortName, String name, String city, String country) {
		this.name = name;
		this.shortName = shortName;
		City = city;
		Country = country;
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
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

	@Override
	public String toString() {
		return "Airport{" +
				"name='" + name + '\'' +
				", shortName='" + shortName + '\'' +
				", City='" + City + '\'' +
				", Country='" + Country + '\'' +
				'}';
	}
}
