package com.tce.kisanbandhu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_login_master")
public class Login {

	@Id
	@Column(name="email")
	private String email;
	
	@Column(name="first_name")
	private String first_name;
	
	@Column(name="last_name")
	private String last_name;

	@Column(name="phone_number")
	private String phone_number;
	
	@Column(name="password")
	private String password;
	
	@Column(name="enrolled_date")
	private String enrolled_date;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEnrolled_date() {
		return enrolled_date;
	}

	public void setEnrolled_date(String enrolled_date) {
		this.enrolled_date = enrolled_date;
	}
}
