package net.aktivreisen24.model;

import java.util.List;

public class Vacation {

    private final long id;
    private final long provider_id;
    private String street;
    private String country;

    private float price;
    private float rating;
    private String generelInfo;
    private String description;

    private List<Comment> comments;

    //availabilty und comments fehlt

    public Vacation(long id, long provider_id, String street, String country,float price, float rating, String generelInfo, String description) {
        this.id = id;
        this.provider_id = provider_id;
        this.street = street;
        this.country = country;
        this.price = price;
        this.rating = rating;
        this.generelInfo = generelInfo;
        this.description = description;
    }

    public long getId() {return id;}

    public long getProviderId() {return provider_id;}

    public String getStreet() {
        return street;
    }

    public String getCountry() {
        return country;
    }

    public float getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }

    public String getGenerelInfo() {
        return generelInfo;
    }

    public String getDescription() {
        return description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setGenerelInfo(String generelInfo) {
        this.generelInfo = generelInfo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
