package com.test.microservice.restfulwebservice.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	@Size(min=2, message="Name should have at least 2 characters")
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
	
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
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
