package net.aktivreisen24.model;

import java.util.List;

public class Vacation {

    private final long id;
    private final long provider_id;
    private String street;
    private String country;

    private int zipCode;
    private String city;

    private float price;
    private float rating;

    private float bestSeason;

    private List<Comment> comments;

    //availabilty und comments fehlt

    public Vacation(long id, long provider_id, String street, String country, float price, float rating) {
        this.id = id;
        this.provider_id = provider_id;
        this.street = street;
        this.country = country;
        this.price = price;
        this.rating = rating;
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

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public long getProvider_id() {
        return provider_id;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getBestSeason() {
        return bestSeason;
    }

    public void setBestSeason(float bestSeason) {
        this.bestSeason = bestSeason;
    }
}
