package net.aktivreisen24.model;

public class Activity {

    private String street;
    private Short houseNumber;
    private String location;

    private double rating;
    private String generelInfo;
    private String description;

    //availabilty und comments fehlt

    public Activity(String street, Short houseNumber, String location, double rating, String generelInfo, String description) {
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

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(Short houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setGenerelInfo(String generelInfo) {
        this.generelInfo = generelInfo;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

