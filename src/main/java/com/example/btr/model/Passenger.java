package com.example.btr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class Passenger {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private long  passengerId;
	@Column
	private long customerId;
	@NotEmpty
	@Column
	private String passengerName;
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Column
	private int age;
	@Column
	private long phoneNo;
	@NotEmpty(message="Email can not be empty!")
	@Email(regexp = "^[a-z0-9.-]+@[a-zA-Z.-]+$", message = "This is not valid mail address!")
	//@Column(unique=true)
	private String emailId;
	@NotEmpty
	@Column
	private String address;

	public long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(long passengerId) {
		this.passengerId = passengerId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", passengerName=" + passengerName + ", age=" + age
				+ ", phoneNo=" + phoneNo + ", emailId=" + emailId + ", address=" + address + "]";
	}

	
	

}
