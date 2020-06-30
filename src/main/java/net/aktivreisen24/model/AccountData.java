package net.aktivreisen24.model;

public class AccountData {
    private final long acc_id;

    private String firstName;
    private String lastName;

    private int phoneNumber;
    private String address;
    private String country;

    public AccountData(long acc_id, String firstName, String lastName, int phoneNumber, String address, String country) {
        this.acc_id = acc_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.country = country;
    }

    public long getAcc_id() {
        return acc_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
