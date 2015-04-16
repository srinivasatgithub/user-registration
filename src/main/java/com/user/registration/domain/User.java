package com.user.registration.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {

	@Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column
	private long id;
	
	@Column
	private String name;

	@Column
	private String email;
	
	@Column
	private Date registeredDate;

	public User() {
	}

	public User(String theName, String anEmail, Date theRegisteredDate) {
		name = theName;
		email = anEmail;
		registeredDate = theRegisteredDate;
	}
	
	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	
	

}
