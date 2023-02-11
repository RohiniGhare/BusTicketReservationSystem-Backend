package com.example.btr.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.event.annotation.AfterTestMethod;

import com.example.btr.dto.CustomerLogin;
import com.example.btr.exceptions.CustomerAlreadyExist;
import com.example.btr.exceptions.CustomerNotFoundException;
import com.example.btr.model.Customer;
import com.example.btr.repository.CustomerRepository;

@SpringBootTest
class CustomerServiceImplTest2 {
	
	@Autowired
	private CustomerServiceImpl custServ;
	@Autowired
	private CustomerRepository custRepo;
	private Customer customer;

	@BeforeEach
	void setUp() throws Exception {
		customer = new Customer();
		customer.setAddress("Pune");
		customer.setCustomerName("TestCust");
		customer.setEmailId("testCust@cmp.com");
		customer.setPassword("testcust");
		customer.setPhoneNo(1234567890);
		custRepo.save(customer);
	}

	@AfterEach
	void tearDown() throws Exception {
		custRepo.delete(customer);
	}

	@Test
	void testAddCustomer(){
		Customer customer2 = new Customer();
		customer2.setAddress("Pune");
		customer2.setCustomerName("TestCust2");
		customer2.setEmailId("customer@cmp.com");
		customer2.setPassword("testcust");
		customer2.setPhoneNo(1234567890);
//		System.err.println(customer2);
		
		boolean actualResult = custServ.addCustomer(customer2);
		assertThat(actualResult).isTrue();
		
		custRepo.delete(customer2);
	}
	

	@Test
	void testUpdateCustomer() {
		customer.setCustomerName("Cust");
		assertTrue(custServ.updateCustomer(customer));
	}

	@Test
	void testListCustomer() {
		//custRepo.save(customer);
		List<Customer> list = custServ.listCustomer();
//		System.err.println("list size "+list.size());
//		System.err.println("repo size "+custRepo.findAll().size());
		assertThat(list.size()).isEqualTo(custRepo.findAll().size());
	}

	@Test
	void testGetCustomer() {
		try {
			Customer cus = custServ.getCustomer(customer.getCustomerId());
			assertThat(cus).isNotNull();
		} catch (CustomerNotFoundException e) {
			fail("Fail as CustomerNotFoundException thrown");
		}
		
	}

	@Test
	void testRemoveCustomer() {
		custServ.removeCustomer(customer.getCustomerId());
		Customer cus;
		boolean result;
		try {
			cus = custServ.getCustomer(customer.getCustomerId());
			//fail("Fail as CustomerNotFoundException not thrown");
			result = false;
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			//assertThat(cus).isNull();
			result = true;
		}
		assertTrue(result);
		//user assertThrows
	}

//	@Test
//	void testLoadUserByUsername() {
//		fail("Not yet implemented");
//	}
//
	@Test
	void testGetCustomerByMail() {
		Customer cus = custServ.getCustomerByMail(customer.getEmailId());
		assertThat(cus).isNotNull();
	}

//	@Test
//	void testUpdateCustomerPassword() {
//		
//		System.err.println("before "+customer);
//		CustomerLogin clogin = new CustomerLogin();
//		clogin.setPassword("password");
//		clogin.setUsername("testCust@cmp.com");
//		custServ.updateCustomerPassword(clogin);
//		System.err.println("after "+customer);
//		String expected = passwordEncoder.encode(customer.getPassword());
//		assertThat(expected).isEqualTo(clogin.getPassword());
//	}

}
