package net.aktivreisen24.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Vacation {

    private long id;
    private long owner_id;
    private String street;
    private String country;

    private int zipCode;
    private String city;

    private float price;
    private float rating;

    private String bestSeason;

    private List<Comment> comments;

    //availabilty und comments fehlt

    public Vacation() {
    }

    public Vacation(@JsonProperty("id") long id,
                    @JsonProperty("owner_id") long owner_id,
                    @JsonProperty("street") String street,
                    @JsonProperty("country") String country,
                    @JsonProperty("zipCode") int zipCode,
                    @JsonProperty("city") String city,
                    @JsonProperty("price") float price,
                    @JsonProperty("bestSeason") String bestSeason) {
        this.id = id;
        this.owner_id = owner_id;
        this.street = street;
        this.country = country;
        this.zipCode = zipCode;
        this.city = city;
        this.price = price;
        this.bestSeason = bestSeason;
    }

    public Vacation(long vacation_id, long owner_id, String address, String country, float price, float rating) {

        this.id = vacation_id;
        this.owner_id = owner_id;

        this.country = country;
        this.price = price;
        this.bestSeason = bestSeason;
    }

    public Vacation(long id, long owner_id, String street, int zipCode, String city, String country, float price, float rating, String bestSeason) {
        this.id = id;
        this.owner_id = owner_id;
        this.street = street;
        this.country = country;
        this.zipCode = zipCode;
        this.city = city;
        this.price = price;
        this.rating = rating;
        this.bestSeason = bestSeason;
    }

    public long getId() {
        return id;
    }

    public long getOwner_id() {
        return owner_id;
    }

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

    public String getBestSeason() {
        return bestSeason;
    }

    public void setBestSeason(String bestSeason) {
        this.bestSeason = bestSeason;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }
}
