package com.example.btr.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.btr.dto.AdminLogin;
import com.example.btr.dto.CustomerLogin;
import com.example.btr.dto.ForgotPassword;
import com.example.btr.exceptions.CustomerAlreadyExist;
import com.example.btr.exceptions.CustomerNotFoundException;
import com.example.btr.model.Admin;
import com.example.btr.model.Customer;
import com.example.btr.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	//@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	@Override
	@Transactional
	public boolean addCustomer(Customer cus) {
		Customer checkCustomer = this.customerRepository.findByEmailId(cus.getEmailId());
		//System.err.println("check "+checkCustomer);
		if(checkCustomer == null) {
			cus.setPassword(passwordEncoder.encode(cus.getPassword()));
			this.customerRepository.save(cus);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		if(customer!=null)
			this.customerRepository.save(customer);
		return true;
	}

	@Override
	public List<Customer> listCustomer() {
		return this.customerRepository.findAll();
	}

	@Override
	public Customer getCustomer(long customerId) throws CustomerNotFoundException {
		Customer c = customerRepository.findByCustomerId(customerId);
		if(c==null) {
			throw new CustomerNotFoundException("");
		}
		return c;
	}

	@Override
	public boolean removeCustomer(long customerId) {
		Customer cust = this.customerRepository.findByCustomerId(customerId);
		if(cust!=null) {
			this.customerRepository.delete(cust);
			return true;
		}
		return false;
	}

//	@Override
//	public Customer validateCustomer(CustomerLogin customerlogin) {
//		Customer customer = this.customerRepository.findByEmailId(customerlogin.getUsername());
//		
//		if(customer == null) {
//			return null;
//		}
//		else {
//			if(customer.getPassword().equals(customerlogin.getPassword())) {
//				return customer;
//			}
//		}
//		
//		return null;
//	}

//	@Override
//	public Customer forgotPassword(ForgotPassword forgotPassword) {
//		Customer customer = this.customerRepository.findByEmailId(forgotPassword.getEmailId());
//		if(customer == null) {
//			return null;
//		}
//		else {
//				return customer;
//			}
//	}

//	@Override
//	public void updatePassword(Customer customer) {
//		if(customer!=null)
//			this.customerRepository.save(customer);
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = this.customerRepository.findByEmailId(username);
		
//		  System.err.println("in loadUserByUsername");
//		  System.err.println("cuxtomer "+customer.getEmailId());
//		  System.err.println("username "+username);
		 
		if(customer==null) {
			throw new UsernameNotFoundException("Customer not found!!!");
		}
		
		return new org.springframework.security.core.userdetails.User(customer.getEmailId(),customer.getPassword(), getAuthorities());
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("CUSTOMER"));
	}
	
	@Override
	public Customer getCustomerByMail(String email) {
		Customer customer = customerRepository.findByEmailId(email);
		return customer;
	}
	
	@Override
	public void updateCustomerPassword(CustomerLogin customerlogin) {
		Customer cust =this.customerRepository.findByEmailId(customerlogin.getUsername());
		if(cust != null) {
			//cust.setPassword(customerlogin.getPassword());
			cust.setPassword(passwordEncoder.encode(customerlogin.getPassword()));
		
			this.customerRepository.save(cust);
		}
	}

}
