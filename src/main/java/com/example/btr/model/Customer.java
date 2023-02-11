package com.example.btr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CustomerTable")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long customerId;
	@NotEmpty(message="Name can not be blank!")
	private String customerName;
	@Size(min=10, max=10, message="Contact number not valid")
	private long phoneNo;
	@NotEmpty(message="Email can not be empty!")
	@Email(regexp = "^[a-z0-9.-]+@[a-zA-Z.-]+$", message = "This is not valid mail address!")
	private String emailId;
	@NotEmpty(message="Password can not be empty!")
	@Size(min = 4, max = 10, message = "Password should be minimum 4 characters!")
	private String password;
	@NotEmpty(message="Address can not be empty!")
	private String address;
	
	public Customer() {
		
	}

	public Customer(long customerId, @NotEmpty(message = "Name can not be blank!") String customerName,
			@Size(min = 10, max = 10, message = "Contact number not valid") long phoneNo,
			@NotEmpty(message = "Email can not be empty!") @Email(regexp = "^[a-z0-9.-]+@[a-zA-Z.-]+$", message = "This is not valid mail address!") String emailId,
			@NotEmpty(message = "Password can not be empty!") @Size(min = 4, max = 10, message = "Password should be minimum 4 characters!") String password,
			@NotEmpty(message = "Address can not be empty!") String address) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.phoneNo = phoneNo;
		this.emailId = emailId;
		this.password = password;
		this.address = address;
	}




	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", phoneNo=" + phoneNo
				+ ", emailId=" + emailId + ", password=" + password + ", address=" + address + "]";
	}

	
	
	

}
