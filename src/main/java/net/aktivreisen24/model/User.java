package net.aktivreisen24.model;

import java.util.UUID;

public class User {

    private final String firstName;
    private final String surname;
    private final UUID id;

    private final String street;
    private final Short houseNumber;
    private final String location;

    private final int phoneNumber;

    private final PaymentInfo pi;

    public User(String firstName, String surname, UUID id, String street, Short houseNumber, String location, int phoneNumber, PaymentInfo pi) {
        this.firstName = firstName;
        this.surname = surname;
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.pi = pi;
    }

    public UUID getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getStreet() {
        return street;
    }

    public Short getHouseNumber() {
        return houseNumber;
    }

    public String getLocation() {
        return location;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }


}
