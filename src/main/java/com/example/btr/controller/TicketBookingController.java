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
import com.example.btr.model.Admin;
import com.example.btr.model.Bus;
import com.example.btr.model.Customer;
import com.example.btr.model.Passenger;
import com.example.btr.model.TicketBooking;
import com.example.btr.service.BusService;
import com.example.btr.service.CustomerService;
import com.example.btr.service.PassengerService;
import com.example.btr.service.TicketBookingService;


@Controller
@RequestMapping("/customer")
public class TicketBookingController {
	public Logger log = Logger.getLogger(TicketBookingController.class);
	
	@Autowired
	private TicketBookingService bookingService;
	@Autowired
	private BusService busService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PassengerService passengerService;
	
	@RequestMapping("/ticket-booking-records")
	public String bookingRecordsShow() {
		return "ticket-booking-records";
	}
	
	
	
	@RequestMapping(path="/selectBuses/{customerId}",method=RequestMethod.POST)
	public ModelAndView busesForCustomer(@PathVariable("customerId") long customerId,@ModelAttribute TicketBooking ticketBooking,Model model,HttpServletRequest request,ModelAndView moView) {
		log.info("In select buses");
		log.info("Request attribute is "+request.getAttribute("customer"));
		log.info("path variable is  "+ customerId);
		List<Bus> buslist = this.bookingService.busesSuitable(ticketBooking);
		if(buslist!=null) {
			log.info(buslist);
			model.addAttribute("customerId", customerId);
			model.addAttribute("buses", buslist);
			log.info("added to model in ticket booking controller");
			moView.setViewName("/customer/show-available-buses");
		}else {
			moView.setViewName("errorPage");
		}
		return moView;
	}
	
	@RequestMapping("/showAvailableBuses")
	public String showAvailableBuses() {
		return "show-availble-buses";
	}
	
	@RequestMapping(path="/selectBuses/{customerId}/makePayment/{busId}", method=RequestMethod.POST)
	public ModelAndView finalBooking(@PathVariable("customerId") long customerId,@PathVariable("busId") long busId ,Model model,@ModelAttribute("passenger") Passenger passenger,ModelAndView view,@ModelAttribute("ticketBooking")TicketBookingDto ticketBooking) {
		log.info("path variables are \n customer Id : "+customerId+"\n busId : "+busId);
		log.info("In the finalbooking method with id "+busId);
		Bus b = this.busService.getBus(busId);
		log.info("bus "+b);
		log.info("In finalBooking method after adding model");
		model.addAttribute("bus", b);
		model.addAttribute("ticketBooking", ticketBooking);
		view.setViewName("/customer/makePayment");
		return view;
	}
	
//	@RequestMapping("/selected/{busId}/fillBookingDetails")
//	public String getBookingDetails() {
//		return "fillBookingDetails";
//	}
	
	@RequestMapping("/ticket-booking")
	public String booking(@ModelAttribute("ticketBooking")TicketBooking ticketBooking) {
		return "ticket-booking";
	}
	
	
	
	@RequestMapping("/selectBuses/{customerId}/makePayment/{busId}/showBill")
	public String bill(@PathVariable("customerId")long customerId,@PathVariable("busId")long busId,Model model) {
		Customer c;
		try {
			c = this.customerService.getCustomer(customerId);
			model.addAttribute("c", c);
			List<Passenger> pList = this.passengerService.getPassengerByCustomer(customerId);
			log.info(pList);
			model.addAttribute("plist", pList);
			Bus b = this.busService.getBus(busId);
			double totalBill = (b.getPricePerSeat()*(pList.size()+1));
			model.addAttribute("totalBill",totalBill);
			model.addAttribute("busId", busId);
			model.addAttribute("pricePerSeat", b.getPricePerSeat());
			model.addAttribute("busName", b.getBusName());
			return "/customer/generate-bill";
		} catch (CustomerNotFoundException e) {
			model.addAttribute("errMsg", "Customer not found");
			return "errorPage";
		}
		
	}
	
	@RequestMapping("/selectBuses/{customerId}/makePayment/{busId}/deletePassenger/showBill")
	public String billFromDeletePassengerList(@PathVariable("customerId")long customerId,@PathVariable("busId")long busId,Model model) {
		Customer c;
		try {
			c = this.customerService.getCustomer(customerId);
			model.addAttribute("c", c);
			List<Passenger> pList = this.passengerService.getPassengerByCustomer(customerId);
			log.info(pList);
			model.addAttribute("plist", pList);
			Bus b = this.busService.getBus(busId);
			double totalBill = (b.getPricePerSeat()*(pList.size()+1));
			model.addAttribute("totalBill",totalBill);
			model.addAttribute("busId", busId);
			model.addAttribute("pricePerSeat", b.getPricePerSeat());
			model.addAttribute("busName", b.getBusName());
			return "/customer/generate-bill";
		} catch (CustomerNotFoundException e) {
			model.addAttribute("errMsg", "Customer not found");
			return "errorPage";
		}
		
	}
	
	
	@RequestMapping(path="/selectBuses/{customerId}/makePayment/{busId}/feedback",method=RequestMethod.GET)
	public String feedbackpage()
	{
		return "/customer/feedback";
	}
	
	@RequestMapping(path="/selectBuses/{customerId}/makePayment/{busId}/deletePassenger/feedback",method=RequestMethod.GET)
	public String feedbackpageFromDeletePassanger()
	{
		return "/customer/feedback";
	}

}
