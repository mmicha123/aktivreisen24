package net.aktivreisen24.model;

public class Provider {

    private final long id;
    private final long acc_id;
    private String name;
    private float rating;

    public Provider(long id, long acc_id, String name, float rating) {
        this.id = id;
        this.acc_id = acc_id;
        this.name = name;
        this.rating = rating;
    }

    public Provider(long id, long acc_id, String name){
        this.id = id;
        this.acc_id = acc_id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public long getAccId() {
        return acc_id;
    }

    public float getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
