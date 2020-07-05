package net.aktivreisen24.model;

import java.util.List;

public class Activity {

    private long id;
    private final long provider_id;

    private float price;
    private float rating;
    private String description;
    private String category;
    private String neededEquip;
    private int amtPeople;

    private List<String> picturesURL;
    private List<String> comments;

    public Activity(long id, long provider_id, float price, float rating, String description, String category, String neededEquip, int amtPeople) {
        this.id = id;
        this.provider_id = provider_id;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.category = category;
        this.neededEquip = neededEquip;
        this.amtPeople = amtPeople;
    }

    public Activity(long provider_id, float price, float rating, String description, String category, String neededEquip, int amtPeople) {
        this.provider_id = provider_id;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.category = category;
        this.neededEquip = neededEquip;
        this.amtPeople = amtPeople;
    }

    public Activity(long provider_id, float price, String description, String category, String neededEquip, int amtPeople) {
        this.provider_id = provider_id;
        this.price = price;
        this.description = description;
        this.category = category;
        this.neededEquip = neededEquip;
        this.amtPeople = amtPeople;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProvider_id() {
        return provider_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNeededEquip() {
        return neededEquip;
    }

    public void setNeededEquip(String neededEquip) {
        this.neededEquip = neededEquip;
    }

    public int getAmtPeople() {
        return amtPeople;
    }

    public void setAmtPeople(int amtPeople) {
        this.amtPeople = amtPeople;
    }

    public List<String> getPicturesURL() {
        return picturesURL;
    }

    public void setPicturesURL(List<String> picturesURL) {
        this.picturesURL = picturesURL;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}

