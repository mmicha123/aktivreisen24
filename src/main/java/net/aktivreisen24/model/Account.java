package net.aktivreisen24.model;

public class Account {
    private final long acc_id;
    private final String passhash;
    private String email;

    public long getAcc_id() {
        return acc_id;
    }

    public String getPasshash() {
        return passhash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account(long acc_id, String passhash, String email) {
        this.acc_id = acc_id;
        this.passhash = passhash;
        this.email = email;
    }
}
