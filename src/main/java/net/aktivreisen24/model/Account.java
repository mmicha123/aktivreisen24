package net.aktivreisen24.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
	public enum Role {
		Admin,
		User,
		Provider
	}

	private final long acc_id;
	private final String passhash;
	private String email;

	private final Role role;

	public Account(@JsonProperty("acc_id") long acc_id,
	               @JsonProperty("password") String passhash,
	               @JsonProperty("email") String email, Role role) {
		this.acc_id = acc_id;
		this.passhash = passhash;
		this.email = email;
		this.role = role;
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

	public Role getRole() {
		return role;
	}

}
