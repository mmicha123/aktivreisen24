package net.aktivreisen24.model;

public class Provider {

    private final String name;
    private final double rating;

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



}
