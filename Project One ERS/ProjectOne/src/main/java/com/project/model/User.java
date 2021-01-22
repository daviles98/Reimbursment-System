package com.project.model;

public class User {
	
	private int userId;
	private String username;
	private String pw;
	private String firstName;
	private String lastName;
	private String email;
	private int userRole;
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}


	public User(int userId, String username, String pw, String firstName, String lastName, String email, int userRole) {
		super();
		this.userId = userId;
		this.username = username;
		this.pw = pw;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getUserRole() {
		return userRole;
	}


	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", pw=" + pw + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", userRole=" + userRole + "]";
	}
	
	
	

}
