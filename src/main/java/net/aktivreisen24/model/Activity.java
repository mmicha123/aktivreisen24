package net.aktivreisen24.model;

public class Activity {

    private final long id;
    private final long provider_id;
    private String street;
    private String country;

    private float price;
    private float rating;
    private String generalInfo;
    private String description;

    //availabilty und comments fehlt

    public Activity(long id, long provider_id, String street, String country, float price, float rating, String generalInfo, String description) {
        this.id = id;
        this.provider_id = provider_id;
        this.street = street;
        this.country = country;
        this.price = price;
        this.rating = rating;
        this.generalInfo = generalInfo;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public long getProvider_id() {
        return provider_id;
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

    public String getGeneralInfo() {
        return generalInfo;
    }

    public String getDescription() {
        return description;
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

    public void setGeneralInfo(String generalInfo) {
        this.generalInfo = generalInfo;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

