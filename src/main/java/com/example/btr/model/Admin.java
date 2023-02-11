package com.example.btr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long adminId;
	@NotEmpty(message="Name can not be blank!")
	private String adminName;
	@Min(value=10,message="Contact number not valid")
	private long phoneNo;
	@NotEmpty(message="Email can not be empty!")
	@Email(regexp = "^[a-z0-9.-]+@[a-zA-Z.-]+$", message = "This is not valid mail address!")
	private String emailId;
	@NotEmpty(message="Password can not be empty!")
	@Size(min = 4, max = 10, message = "Password should be minimum 4 characters!")
	private String password;
	
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", phoneNo=" + phoneNo + ", emailId="
				+ emailId + ", password=" + password + "]";
	}


	public long getAdminId() {
		return adminId;
	}
	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
		
}
