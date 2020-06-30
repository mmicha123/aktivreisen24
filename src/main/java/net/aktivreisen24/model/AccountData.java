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



}
