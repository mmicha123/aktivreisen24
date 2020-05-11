package net.aktivreisen24.model;

import java.util.Date;

public class User {
    private long user_id;
    private String firstname;
    private String lastname;
    private int phone;
    //private String address;
    //private String city;
    //private int zip_code;
    private String country;
    private Date geb_dat;

    public User(String firstname, String lastname) {

    }

    public long getUser_id() {
        return user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getGeb_dat() {
        return geb_dat;
    }

    public void setGeb_dat(Date geb_dat) {
        this.geb_dat = geb_dat;
    }
}
