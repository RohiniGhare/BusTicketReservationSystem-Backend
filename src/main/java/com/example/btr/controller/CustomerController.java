package com.example.btr.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.View;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.btr.dto.CustomerLogin;
import com.example.btr.dto.ForgotPassword;
import com.example.btr.dto.TicketBookingDto;
import com.example.btr.exceptions.CustomerNotFoundException;
import com.example.btr.model.Customer;
import com.example.btr.model.TicketBooking;
import com.example.btr.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	public Logger log = Logger.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;
	
	
	 @RequestMapping("/registerCustomer")
	    public String registration(@ModelAttribute("customer")Customer customer, Model model,ModelAndView view) {
	       model.addAttribute("userForm", new Customer());
	        return "/customer/registerCustomer";
	       //view.setViewName("registerCustomer");
	       //return view;
	    }
	 
	 @RequestMapping("/home")
	 public ModelAndView homeCustomer(Model model,@ModelAttribute("ticketBooking")TicketBookingDto ticketBooking,ModelAndView view,Principal principal)
		{
		 log.info("Inside home");
			/* System.err.println("Priciple "+principal.getName()); */
			//List<Customer> customer = customerService.listCustomer();
			Customer cus = customerService.getCustomerByMail(principal.getName());
			model.addAttribute("customerId", cus.getCustomerId());
			model.addAttribute("ticketBooking", ticketBooking);
			view.setViewName("/customer/home");
			return view;
			//return "/customer/home";
		}
	 
	 @RequestMapping("/selectBuses/home")
		public String homeCustomerFromSelectBus(Model model,@ModelAttribute("ticketBooking")TicketBooking ticketBooking)
		{
			List<Customer> customer = customerService.listCustomer();
			model.addAttribute("customer", customer);
			model.addAttribute("ticketBooking", ticketBooking);
			return "redirect:/customer/home";
		}
	 
	 
	 @RequestMapping("/selectBuses/{customerId}/makePayment/home")
		public String homeCustomerFromMakePayment(Model model,@ModelAttribute("ticketBooking")TicketBooking ticketBooking)
		{
			List<Customer> customer = customerService.listCustomer();
			model.addAttribute("customer", customer);
			model.addAttribute("ticketBooking", ticketBooking);
			return "redirect:/customer/home";
		}
	 
	 @RequestMapping("/selectBuses/{customerId}/makePayment/{busId}/deletePassenger/home")
		public String homeCustomerFromDeletePassenger(Model model,@ModelAttribute("ticketBooking")TicketBooking ticketBooking)
		{
			List<Customer> customer = customerService.listCustomer();
			model.addAttribute("customer", customer);
			model.addAttribute("ticketBooking", ticketBooking);
			return "redirect:/customer/home";
		}
	 
	 @RequestMapping("/selectBuses/{customerId}/makePayment/{busId}/home")
		public String homeCustomerFromPassengerDetails(Model model,@ModelAttribute("ticketBooking")TicketBooking ticketBooking)
		{
			List<Customer> customer = customerService.listCustomer();
			model.addAttribute("customer", customer);
			model.addAttribute("ticketBooking", ticketBooking);
			return "redirect:/customer/home";
		}
	 
	 @RequestMapping("/selectBuses/{customerId}/makePayment/{busId}/feedback/home")
		public String homeCustomerFromFeedback(Model model,@ModelAttribute("ticketBooking")TicketBooking ticketBooking)
		{
			List<Customer> customer = customerService.listCustomer();
			model.addAttribute("customer", customer);
			model.addAttribute("ticketBooking", ticketBooking);
			return "redirect:/customer/home";
		}
	 
	 
	 
	    @RequestMapping(path="/saveCustomer", method=RequestMethod.POST)
		public String handelRegisterFormCustomer(HttpSession session,HttpServletRequest request ,@Valid @ModelAttribute("userForm") Customer userForm,BindingResult result) {
			/*
			 * if(this.customerService.addCustomer(userForm)) { session =
			 * request.getSession(); session.setAttribute("customer", userForm); //return
			 * "/customer/home"; view.setViewName("home"); return view; }else {
			 * session.setAttribute("invalidRegister", "Admin already exist !!"); //return
			 * "registerCustomer"; view.setViewName("registerCustomer"); return view; }
			 */
	    	
	    	log.info(userForm);
			if(result.hasErrors()) {
				log.info(result);
				return "/customer/registerCustomer";
			}
			else {
				
				if(this.customerService.addCustomer(userForm)) {
					log.info("Customer added");
					session = request.getSession();
//					session.setAttribute("customerId", userForm.getCustomerId());
					return "index_customer";
				}else {
					session.setAttribute("invalidRegister", "Customer already exist !!");
					log.info("Customer already exist !!!");
					return "/customer/registerCustomer";
				}
			}
	    }
	    
	    
	    @RequestMapping(value = "/loginCustomer")
	    public String customerLoginPage(@ModelAttribute("customerlogin") CustomerLogin customerlogin,HttpSession session) {
	    	session.removeAttribute("invalidLogin");
	      return "/customer/loginCustomer";
	    }
	    
//	    @RequestMapping(path="/loginValidateCustomer",method=RequestMethod.POST)
//		public ModelAndView loginCustomer(Model model,HttpServletRequest request,HttpSession session,@Valid @ModelAttribute("customerlogin") CustomerLogin customerlogin,Errors error,ModelAndView view) { //@RequestParam("emailId") String emailId,@RequestParam("password") String pass,HttpServletRequest request) {
//			
//			Customer customer = this.customerService.validateCustomer(customerlogin);
//			if(customer != null) {
//				session = request.getSession();
//				session.setAttribute("customerId", customer.getCustomerId());
//				model.addAttribute("customer", customer);
//				view.setViewName("redirect:/customer/home");
//				return view;
//			}
//			else {
//				session.setAttribute("invalidLogin", "invalid Credentials!");
//				view.setViewName("/customer/loginCustomer");
//				return view;
//			}
//		}
	    
		@RequestMapping(value="/logoutCustomer", method=RequestMethod.GET)
	    public RedirectView  logOut(HttpServletRequest request) {
			HttpSession session =request.getSession();
			session.invalidate();
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl(request.getContextPath() + "/customer/loginCustomer");
			return redirectView;
		}
		
		@RequestMapping(value="/selectBuses/logoutCustomer", method=RequestMethod.GET)
	    public RedirectView  logOutFromBusList(HttpServletRequest request) {
			HttpSession session =request.getSession();
			session.invalidate();
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl(request.getContextPath() + "/customer/loginCustomer");
			return redirectView;
		}
		
		@RequestMapping(value="/selectBuses/{customerId}/makePayment/{busId}/logoutCustomer", method=RequestMethod.GET)
	    public RedirectView  logOutFromBusPaymentDetails(HttpServletRequest request) {
			HttpSession session =request.getSession();
			session.invalidate();
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl(request.getContextPath() + "/customer/loginCustomer");
			return redirectView;
		}
	    
		@RequestMapping(value="/selectBuses/{customerId}/makePayment/logoutCustomer", method=RequestMethod.GET)
	    public RedirectView  logOutFromMakePaymentDetails(HttpServletRequest request) {
			HttpSession session =request.getSession();
			session.invalidate();
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl(request.getContextPath() + "/customer/loginCustomer");
			return redirectView;
		}
		
		@RequestMapping(value="/selectBuses/{customerId}/makePayment/{busId}/deletePassenger/logoutCustomer", method=RequestMethod.GET)
	    public RedirectView  logOutFromDeletePassenger(HttpServletRequest request) {
			HttpSession session =request.getSession();
			session.invalidate();
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl(request.getContextPath() + "/customer/loginCustomer");
			return redirectView;
		}
		
		
	    
	    @RequestMapping("/profileCustomer/{customerId}")
		public ModelAndView customerProfile(@PathVariable("customerId") long customerId ,Model model,ModelAndView moView) {
	    	log.info("customerId : "+customerId);
			Customer customer;
			try {
				customer = this.customerService.getCustomer(customerId);
				log.info("Customer : "+customer);
				model.addAttribute("customer", customer);
				model.addAttribute("customerId", customerId);
				moView.setViewName("/customer/updateProfile");
				return moView;
			} catch (CustomerNotFoundException e) {
				model.addAttribute("errMsg", "Customer not found");
				moView.setViewName("errorPage");
				return moView;	
			}
			
		}

	    @RequestMapping("/profileCustomer/validateUpdateCustomer/{customerId}")
		public String upadtecustomer(@Valid @ModelAttribute("customer")Customer customer, Errors errors,Model model) {
			log.info("In validate customer");
			log.info("customer after update"+customer);
			if(errors.hasErrors()) {
				log.info(errors);
				return "/customer/profile/{customerId}";
			}
			else {
				if(this.customerService.updateCustomer(customer)) {
					return "redirect:/customer/home";
				}else {
					model.addAttribute("errMsg", "Profile update failed..... Try after sometime....");
					return "redirect:/errorPage";
				}
			}
			
		}
	    	 
		@RequestMapping(value="/profileCustomer/delete-Customer/{customerId}", method = RequestMethod.POST)
		public RedirectView deleteCustomer(@PathVariable("customerId") long customerId,HttpServletRequest request)
		{
			this.customerService.removeCustomer(customerId);
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl(request.getContextPath() + "/");
			return redirectView;
		}
		
		
		 @RequestMapping(value = "/forgotPasswordCustomer")
		    public String passwordPage(@ModelAttribute("customerlogin") CustomerLogin customerlogin,HttpSession session) {
		    	session.removeAttribute("invalidLogin");
		      return "/customer/forgotPasswordCustomer";
		    }
		
		
		 @RequestMapping(path="/passwordchange", method=RequestMethod.POST)
			public String forgotPassword(HttpSession session ,@Valid @ModelAttribute("customerlogin") CustomerLogin customerlogin,BindingResult result,Errors error) {
			 Customer customer = this.customerService.getCustomerByMail(customerlogin.getUsername());
					if(customer != null) {
						this.customerService.updateCustomerPassword(customerlogin);
						return "redirect:/customer/loginCustomer";
					}else {
						session.setAttribute("invalid", "Invalid Customer Credentials !!");
						return "/customer/forgotPasswordCustomer";
					}
		    }
	
}
