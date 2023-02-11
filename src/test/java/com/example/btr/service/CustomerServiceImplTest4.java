package com.example.btr.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.btr.dto.CustomerLogin;
import com.example.btr.exceptions.CustomerAlreadyExist;
import com.example.btr.exceptions.CustomerNotFoundException;
import com.example.btr.model.Customer;
import com.example.btr.repository.CustomerRepository;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CustomerServiceImplTest4 {

	@Mock
	private CustomerRepository custRepo;
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	@InjectMocks
	private CustomerServiceImpl custServ;
	private Customer customer;
	
	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

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

//	@Test
//	void testCustomerServiceImpl() {
//		fail("Not yet implemented");
//	}

	@Test
	void testAddCustomer() {
		Customer customer2 = new Customer();
		customer2.setCustomerId(1001);
		customer2.setAddress("Pune");
		customer2.setCustomerName("TestCust2");
		customer2.setEmailId("customer@cmp.com");
		customer2.setPassword("testcust");
		customer2.setPhoneNo(1234567890);
		
		//when(passwordEncoder.encode(customer2.getPassword())).thenReturn(passwordEncoder.encode(customer2.getPassword()));
		
		boolean result = false;
		result = custServ.addCustomer(customer2);
		
		verify(custRepo, times(1)).save(customer2);
		assertTrue(result);
	}

	@Test
	void testUpdateCustomer() {
		Customer updatedCustomer = customer;
		updatedCustomer.setCustomerName("CustomerUpdated");
		when(custRepo.save(customer)).thenReturn(updatedCustomer);
		
		boolean result = custServ.updateCustomer(updatedCustomer);
		
		assertTrue(result);
		verify(custRepo, times(2)).save(updatedCustomer);
	}

	@Test
	void testListCustomer() {
		List<Customer> clist = new ArrayList();
		clist.add(new Customer(101, "cust1", 1234567890, "cust1@gmail.com", "cust1", "Pune"));
		clist.add(new Customer(102, "cust2", 1234567890, "cust2@gmail.com", "cust2", "Pune"));
		clist.add(new Customer(103, "cust3", 1234567890, "cust3@gmail.com", "cust3", "Pune"));
		
		when(custRepo.findAll()).thenReturn(clist);
		
		List<Customer> custList = custServ.listCustomer();
		
		assertEquals(3, custList.size());
		verify(custRepo, times(1)).findAll();
		
	}

	@Test
	void testGetCustomer() throws CustomerNotFoundException {
		when(custRepo.findByCustomerId(1)).thenReturn(new Customer(1,"Customer",1234567890,"cust@gmail.com","customer","Pune"));
		
		Customer cust = custServ.getCustomer(1);
		
		assertEquals("Customer", cust.getCustomerName());
		assertEquals(1234567890, cust.getPhoneNo());
		assertEquals("cust@gmail.com", cust.getEmailId());
		assertEquals("customer", cust.getPassword());
		
	}

	@Test
	void testRemoveCustomer() {
		
		List<Customer> clist = new ArrayList();
		Customer c1 = new Customer(101, "cust1", 1234567890, "cust1@gmail.com", "cust1", "Pune");
		Customer c2 = new Customer(102, "cust2", 1234567890, "cust2@gmail.com", "cust2", "Pune");
		Customer c3 = new Customer(103, "cust3", 1234567890, "cust3@gmail.com", "cust3", "Pune");
		clist.add(c1);
		clist.add(c2);
		clist.add(c3);
		
		when(custRepo.findByCustomerId(103)).thenReturn(c3);
		
		
		boolean result = custServ.removeCustomer(103);
		
		assertTrue(result);
		verify(custRepo,times(1)).delete(c3);
	}

	@Test
	void testLoadUserByUsername() {
		when(custRepo.findByEmailId("testCust@cmp.com")).thenReturn(customer);
		
		User user = (User) custServ.loadUserByUsername("testCust@cmp.com");
		
		assertThat(user).isNotNull();
		
		Collection<? extends GrantedAuthority> role = Collections.singleton(new SimpleGrantedAuthority("CUSTOMER"));
		assertThat(user.getAuthorities()).isEqualTo(role);
		
		assertEquals("testCust@cmp.com", user.getUsername());
		assertEquals("testcust", user.getPassword());
		
	}

	@Test
	void testGetCustomerByMail() {
		when(custRepo.findByEmailId("cust@gmail.com")).thenReturn(new Customer(1,"Customer",1234567890,"cust@gmail.com","customer","Pune"));
		
		Customer cust = custServ.getCustomerByMail("cust@gmail.com");
		
		assertEquals("Customer", cust.getCustomerName());
		assertEquals(1234567890, cust.getPhoneNo());
		assertEquals("cust@gmail.com", cust.getEmailId());
		assertEquals("customer", cust.getPassword());
	}

	@Test
	void testUpdateCustomerPassword() {
		CustomerLogin custlogin = new CustomerLogin();
		custlogin.setUsername("testCust@cmp.com");
		custlogin.setPassword("password");
		
		when(custRepo.findByEmailId("testCust@cmp.com")).thenReturn(customer);
		custServ.updateCustomerPassword(custlogin);
		
		//when(passwordEncoder.encode(custlogin.getPassword())).thenReturn(this.passwordEncoder.encode(custlogin.getPassword()));
		
		verify(custRepo,times(2)).save(customer);
		
	}

}
