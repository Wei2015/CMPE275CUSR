package com.cmpe275.cusr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private long userId;
	@Column(name= "FIRST_NAME", nullable = false)
	private String firstName;
	@Column(name="LAST_NAME", nullable = false)
	private String lastName;
	@Column(name="PASSWORD", nullable = false)
	private String password;
	@Column(name="EMAIL", nullable = false)
	private String email;

}

