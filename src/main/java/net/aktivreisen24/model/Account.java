package net.aktivreisen24.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
	private final long acc_id;
	private final String passhash;
	private String email;

	public Account(@JsonProperty("acc_id") long acc_id,
	               @JsonProperty("password") String passhash,
	               @JsonProperty("email") String email) {
		this.acc_id = acc_id;
		this.passhash = passhash;
		this.email = email;
	}

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

}
