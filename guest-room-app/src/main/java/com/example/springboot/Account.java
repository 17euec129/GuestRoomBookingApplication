package com.example.springboot;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account{
	@Id
  	@GeneratedValue(strategy=GenerationType.AUTO)	
  	public Long		id;
	public String	name;
	public String	username;
	public String	emailId;
	private String	password;
	public String	userType;
	public Long		mobileNumber;

	public Account(){

	}

	public String toString(){
		return this.name+"-"+this.username;
	}
	public Account(String name, String username, String emailId, String password, String userType, Long mobileNumber){
		this.name = name;
		this.username = username;
		this.emailId = emailId;
		this.password = password;
		this.userType = userType;
		this.mobileNumber = mobileNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}