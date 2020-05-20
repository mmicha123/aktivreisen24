package net.aktivreisen24.model;


public class User {

    private final int id;
    private String firstName;
    private String surname;

    private String address;
    private String country;

    private int phoneNumber;

    private PaymentInfo pi;

    public User(int id, String firstName, String surname, String address, String country, int phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public int getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPi(PaymentInfo pi) {
        this.pi = pi;
    }
}
