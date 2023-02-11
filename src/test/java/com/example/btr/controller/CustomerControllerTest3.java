package com.example.btr.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.btr.dto.CustomerLogin;
import com.example.btr.dto.TicketBookingDto;
import com.example.btr.exceptions.CustomerNotFoundException;
import com.example.btr.model.Customer;
import com.example.btr.service.CustomerService;
//import com.sun.org.apache.xerces.internal.impl.dv.xs.AnySimpleDV;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CustomerControllerTest3 {
	
	@Mock
	private CustomerService custServ;
	@Mock
	private BindingResult result;
	@Mock
	private HttpServletRequest req;
	@Mock
	private Customer userForm;
	@Mock
	private MockHttpSession session;
	@Mock
	private Model model;
	@Mock
	private TicketBookingDto ticketBooking;
	@Mock
	private ModelAndView view;
	@Mock
	private Principal principal;
	@Mock
	private Errors errors;
	@Mock
	private HttpServletRequest request;
	
	
	
	@InjectMocks
	private CustomerController custController;

	@Test
	public void testHandelRegisterFormCustomer() {
		
		Customer cus = new Customer(1001, "Customer1", 1234567890, "cust@gmail.com", "password", "Pune");
		
//		MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		/*********************Test1*********************/
        when(result.hasErrors()).thenReturn(true);
        String result1 = custController.handelRegisterFormCustomer(session, req, cus, result);
        assertEquals("/customer/registerCustomer", result1);
        
        /*********************Test1*********************/
        when(custServ.addCustomer(cus)).thenReturn(true);
        when(result.hasErrors()).thenReturn(false);
        
        String result2 = custController.handelRegisterFormCustomer(session, req, cus, result);
        assertEquals("index_customer", result2);
        
        /*********************Test3*********************/
        when(custServ.addCustomer(cus)).thenReturn(false);
        when(result.hasErrors()).thenReturn(false);
        String result3 = custController.handelRegisterFormCustomer(session, req, cus, result);
        assertEquals("/customer/registerCustomer", result3);
	}
	
	@Test
	public void testHomeCustomer() {	
		Customer cust = new Customer(101, "cust1", 1234567890, "cust1@gmail.com", "cust1", "Pune");
		when(custServ.getCustomerByMail(principal.getName())).thenReturn(cust);
		
		ModelAndView v = custController.homeCustomer(model, ticketBooking, view, principal);
		
		System.err.println("View "+v);
		
		assertThat(v).isNotNull();
		verify(view,times(1)).setViewName("/customer/home");
		
	}
	
	@Test
	void testCustomerLoginPage() {
		CustomerLogin custlogin = new CustomerLogin();
		//custlogin.setUsername("");
		
		String result = custController.customerLoginPage(custlogin, session);
		
		assertEquals("/customer/loginCustomer", result);
	}
	
	@Test
	void testCustomerProfile() {
		Customer cust = new Customer(101, "cust1", 1234567890, "cust1@gmail.com", "cust1", "Pune");
		long customerId = cust.getCustomerId();
		try {
			when(custServ.getCustomer(customerId)).thenReturn(cust);
			ModelAndView v = custController.customerProfile(customerId, model, view);
			assertThat(v).isNotNull();
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	void testUpdateCustomer() {
		Customer cust = new Customer(101, "cust1", 1234567890, "cust1@gmail.com", "cust1", "Pune");
		
		when(errors.hasErrors()).thenReturn(true);
		String result1 = custController.upadtecustomer(cust, errors, model);
		assertEquals("/customer/profile/{customerId}", result1);
		
		when(errors.hasErrors()).thenReturn(false);
		when(custServ.updateCustomer(cust)).thenReturn(true);
		String result2 = custController.upadtecustomer(cust, errors, model);
		assertEquals("redirect:/customer/home", result2);
		
		when(errors.hasErrors()).thenReturn(false);
		when(custServ.updateCustomer(cust)).thenReturn(false);
		String result3 = custController.upadtecustomer(cust, errors, model);
		assertEquals("redirect:/errorPage", result3);
		
	}
	
	@Test
	void testDeleteCustomer() {
		Customer cust = new Customer(101, "cust1", 1234567890, "cust1@gmail.com", "cust1", "Pune");
		
		when(custServ.removeCustomer(cust.getCustomerId())).thenReturn(true);
		
		RedirectView redirectView = custController.deleteCustomer(cust.getCustomerId(), request);
		
		assertThat(redirectView).isNotNull();
		assertEquals("null/", redirectView.getUrl());
	}

}
