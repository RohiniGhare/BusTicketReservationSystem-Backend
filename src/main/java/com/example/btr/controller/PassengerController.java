package com.example.btr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.btr.dto.TicketBookingDto;
import com.example.btr.exceptions.CustomerNotFoundException;
import com.example.btr.model.Customer;
import com.example.btr.model.Passenger;
import com.example.btr.service.CustomerService;
import com.example.btr.service.PassengerService;


@Controller
@RequestMapping("/customer")
public class PassengerController {
	
public Logger log = Logger.getLogger(PassengerController.class);
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PassengerService passengerService;
	
	@RequestMapping("/passenger-details")
	public String passengerdeatils() {
		return "/customer/passenger-deatils";
	}
	
	@RequestMapping("/passenger-list")
	public String passengerList() {
		return "/customer/passenger-list";
	}
	
	
	@RequestMapping(path="/selectBuses/{customerId}/makePayment/{busId}/passenger-details", method=RequestMethod.POST)
	public ModelAndView passengerDetails(ModelAndView view,@PathVariable("customerId") long customerId,@PathVariable("busId") long busId, Model model,@Valid @ModelAttribute TicketBookingDto ticketBooking, @ModelAttribute("passenger") Passenger passenger) {
		log.info("tiBookingDto dest: "+ticketBooking.getFromDest());
		log.info("noOfPassengers : "+ticketBooking.getNoOfPassengers());
		log.info("In passenger details method............");
		model.addAttribute("customerId", customerId);
		model.addAttribute("busId", busId);
		model.addAttribute("noOfPassenger", ticketBooking.getNoOfPassengers());
		Customer c;
		try {
			c = this.customerService.getCustomer(customerId);
			model.addAttribute("c", c);
			List<Passenger> pList = this.passengerService.getPassengerByCustomer(customerId);
			model.addAttribute("plist", pList);
			model.addAttribute("currentCount", pList.size());
			log.info("\n plist count : "+pList.size()+"\n");
			view.setViewName("/customer/passenger-details");
			
			return view;
		} catch (CustomerNotFoundException e) {
			model.addAttribute("errMsg", "Customer not found");
			view.setViewName("errorPage");
			return view;	
		}
	}
	
	@RequestMapping(path="/selectBuses/{customerId}/makePayment/{busId}/passenger-details", method=RequestMethod.GET)
	public ModelAndView passengerDetails2(ModelAndView view,@PathVariable("customerId") long customerId,@PathVariable("busId") long busId, Model model,@Valid @ModelAttribute TicketBookingDto ticketBooking, @ModelAttribute("passenger") Passenger passenger) {
		log.info("tiBookingDto dest: "+ticketBooking.getFromDest());
		log.info("noOfPassengers : "+ticketBooking.getNoOfPassengers());
		log.info("In passenger details method............");
		model.addAttribute("customerId", customerId);
		model.addAttribute("busId", busId);
		model.addAttribute("noOfPassenger", ticketBooking.getNoOfPassengers());
		Customer c;
		try {
			c = this.customerService.getCustomer(customerId);
			model.addAttribute("c", c);
			List<Passenger> pList = this.passengerService.getPassengerByCustomer(customerId);
			model.addAttribute("plist", pList);
			view.setViewName("/customer/passenger-details");
			return view;
		} catch (CustomerNotFoundException e) {
			model.addAttribute("errMsg", "Customer not found");
			view.setViewName("errorPage");
			return view;	
		}
	}
	
	
	//selectBuses/6/makePayment/8/passenger-list
	@RequestMapping(path="/selectBuses/{customerId}/makePayment/{busId}/passenger-list")
	public ModelAndView passengerListPage(ModelAndView view,@PathVariable("customerId") long customerId,@PathVariable("busId") long busId,@ModelAttribute("passenger") Passenger passenger,Model model) {
		model.addAttribute("customerId", customerId);
		model.addAttribute("busId", busId);
		try {
			Customer c = this.customerService.getCustomer(customerId);
			model.addAttribute("c", c);
			List<Passenger> pList = this.passengerService.getPassengerByCustomer(customerId);
			model.addAttribute("plist", pList);
			view.setViewName("/customer/passenger-list");
		} catch (CustomerNotFoundException e) {
			model.addAttribute("errMsg", "Customer not found");
			//return "errorPage";
			view.setViewName("errorPage");
		}
		return view;
	}
	
	
	
	@RequestMapping(path="/selectBuses/{customerId}/makePayment/{busId}/deletePassenger/passenger-details")
	public ModelAndView passengerListPageFromDeletePassenger(ModelAndView view,@PathVariable("customerId") long customerId,@PathVariable("busId") long busId,@ModelAttribute("passenger") Passenger passenger,Model model) {
		model.addAttribute("customerId", customerId);
		model.addAttribute("busId", busId);
		try {
			Customer c = this.customerService.getCustomer(customerId);
			model.addAttribute("c", c);
			List<Passenger> pList = this.passengerService.getPassengerByCustomer(customerId);
			model.addAttribute("plist", pList);
			view.setViewName("redirect:/selectBuses/{customerId}/makePayment/{busId}/passenger-details");
		} catch (CustomerNotFoundException e) {
			model.addAttribute("errMsg", "Customer not found");
			//return "errorPage";
			view.setViewName("errorPage");
		}
		return view;
	}
	
	
	@RequestMapping(path="/selectBuses/{customerId}/makePayment/{busId}/savePassenger",method=RequestMethod.POST)
	public ModelAndView savePassengerDeatils(ModelAndView view,@PathVariable("customerId") long customerId,@PathVariable("busId") long busId,@ModelAttribute("passenger") Passenger passenger,Model model,HttpServletRequest request) {
		log.info("Inside save Passenger");
		model.addAttribute("customerId", customerId);
		model.addAttribute("busId", busId);
		Customer c;
		try {
			c = this.customerService.getCustomer(customerId);
			model.addAttribute("c", c);
			this.passengerService.addPassenger(passenger);
			List<Passenger> pList = this.passengerService.getPassengerByCustomer(customerId);
			log.info(pList);
			model.addAttribute("plist", pList);
			log.info("rv set as passenger details");
			//return "redirect:/passenger-details";
			view.setViewName("/customer/passenger-list");
		} catch (CustomerNotFoundException e) {
			model.addAttribute("errMsg", "Customer not found");
			//return "errorPage";
			view.setViewName("errorPage");
		}
		log.info("view return");
		return view;
		
	}
	
	
	
	@RequestMapping(path="/selectBuses/{customerId}/makePayment/{busId}/deletePassenger/{passengerId}",method=RequestMethod.GET)
	public ModelAndView deletePassengerDeatils(ModelAndView view,@PathVariable("customerId") long customerId,@PathVariable("busId") long busId,@ModelAttribute("passenger") Passenger passenger,Model model,HttpServletRequest request,@PathVariable("passengerId")long passengerId) {
		log.info("Inside save Passenger");
		model.addAttribute("customerId", customerId);
		model.addAttribute("busId", busId);
		Customer c;
		try {
			c = this.customerService.getCustomer(customerId);
			model.addAttribute("c", c);
			this.passengerService.removePassenger(passengerId);
			List<Passenger> pList = this.passengerService.getPassengerByCustomer(customerId);
			log.info(pList);
			model.addAttribute("plist", pList);
			view.setViewName("redirect:/customer/selectBuses/{customerId}/makePayment/{busId}/passenger-details");
			RedirectView rv = new RedirectView();
			rv.setUrl(request.getContextPath()+"/selectBuses/{customerId}/makePayment/{busId}/passenger-details");
			log.info("rv set as passenger details");
			//return "redirect:/passenger-details";
			return view;
		} catch (CustomerNotFoundException e) {
			model.addAttribute("errMsg", "Customer not found");
			//return "errorPage";
			return view;
		}
		
	}

}
