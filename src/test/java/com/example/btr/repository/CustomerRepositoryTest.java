package com.example.btr.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.btr.model.Customer;

@SpringBootTest
class CustomerRepositoryTest {
	
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

	@Test
	void testFindByEmailId() {
		Customer cust = custRepo.findByEmailId(customer.getEmailId());
		boolean actualResult=false;
		if(cust!=null) {
			actualResult = true;
		}
//		assertThat(actualResult).isTrue();
		assertTrue(actualResult);
	}

	@Test
	void testFindByCustomerId() {
		Customer cust = custRepo.findByCustomerId(customer.getCustomerId());
		boolean actualResult=false;
		if(cust!=null) {
			actualResult = true;
		}
		assertThat(actualResult).isTrue();
	}
	
	@AfterEach
	void tearDown() {
		custRepo.delete(customer);
	}

}
