package net.aktivreisen24.model;


public class User {

    private long id;
    private final long id_acc;
    private String firstName;
    private String lastName;

    private String address;
    private String country;

    private int phoneNumber;

    private PaymentInfo pi;

    public User(long id, long id_acc, String firstName, String lastName, String address, String country, int phoneNumber) {
        this.id = id;
        this.id_acc = id_acc;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public long getAccId() {
        return id_acc;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
