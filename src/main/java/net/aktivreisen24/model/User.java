package net.aktivreisen24.model;

import java.util.UUID;

public class User {

    private final String name;
    private final UUID id;

    public User(UUID id, String name){
        this.id = id;
        this.name = name;
    }

    public UUID getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
