package net.aktivreisen24.model;

public class Provider {



    private final long id;
    private String name;
    private double rating;

    public Provider(long id, String name, double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }
    public long getId() {
        return id;
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
