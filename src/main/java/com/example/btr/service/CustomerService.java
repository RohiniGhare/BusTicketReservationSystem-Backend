package com.example.btr.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.btr.dto.CustomerLogin;
import com.example.btr.dto.ForgotPassword;
import com.example.btr.exceptions.CustomerAlreadyExist;
import com.example.btr.exceptions.CustomerNotFoundException;
import com.example.btr.model.Customer;

public interface CustomerService extends UserDetailsService {
	
	public boolean addCustomer(Customer cus);
	public boolean updateCustomer(Customer customer);
	public List<Customer> listCustomer();
	public Customer getCustomer(long customerId) throws CustomerNotFoundException;
	public boolean removeCustomer(long customerId);
	//public Customer validateCustomer(CustomerLogin customerlogin);
	//public Customer forgotPassword(ForgotPassword forgotPassword);
	//public void updatePassword(Customer customer);
	Customer getCustomerByMail(String email);
	void updateCustomerPassword(CustomerLogin customerlogin);

}
