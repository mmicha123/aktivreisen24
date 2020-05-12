package net.aktivreisen24.model;

public class Vacation {

    private final String street;
    private final Short houseNumber;
    private final String location;

    private final double rating;
    private final String generelInfo;
    private final String description;

    //availabilty und comments fehlt


    public Vacation(String street, Short houseNumber, String location, double rating, String generelInfo, String description) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.location = location;
        this.rating = rating;
        this.generelInfo = generelInfo;
        this.description = description;
    }

    public String getStreet() {
        return street;
    }

    public Short getHouseNumber() {
        return houseNumber;
    }

    public String getLocation() {
        return location;
    }

    public double getRating() {
        return rating;
    }

    public String getGenerelInfo() {
        return generelInfo;
    }

    public String getDescription() {
        return description;
    }
}
