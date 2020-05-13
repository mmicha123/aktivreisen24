package net.aktivreisen24.model;

import java.util.UUID;

public class User {

    private String firstName;
    private String surname;
    private UUID id;

    private String street;
    private Short houseNumber;
    private String location;

    private int phoneNumber;

    private PaymentInfo pi;

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(Short houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPi(PaymentInfo pi) {
        this.pi = pi;
    }
}
