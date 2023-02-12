package com.example.jwt.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class User {
	
	@Id
	@GeneratedValue
	private long id;
	private String username;
	private String password;
	private String roles;
}
