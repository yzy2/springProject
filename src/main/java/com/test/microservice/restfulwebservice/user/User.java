package com.test.microservice.restfulwebservice.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {
	private Integer id;
	@Size(min=2)
	private String name;
	@Past
	private Date birthData;
	
	// Default no argument constructor
	protected User() {
		
	}
	
	public User(Integer id, String name, Date birthData) {
		super();
		this.id = id;
		this.name = name;
		this.birthData = birthData;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthData() {
		return birthData;
	}
	public void setBirthData(Date birthData) {
		this.birthData = birthData;
	}
	
	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthData=%s]", id, name, birthData);
	}
	
	
}
