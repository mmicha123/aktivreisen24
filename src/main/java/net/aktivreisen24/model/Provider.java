package net.aktivreisen24.model;

public class Provider {

    private String name;
    private double rating;

    public Provider(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    //List für vacation/activity


    public void setName(String name) {
        this.name = name;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
