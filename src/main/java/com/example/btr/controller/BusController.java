package com.example.btr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.btr.exceptions.ResourceNotFoundException;
import com.example.btr.model.Admin;
import com.example.btr.model.Bus;
import com.example.btr.service.BusService;

//@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class BusController {
	public Logger log = Logger.getLogger(BusController.class);
	
	@Autowired
	private BusService busService;
	
//	@RequestMapping("/addBus")
//	public String addBuses(@ModelAttribute("bus") Bus bus) {
//		log.info("In Admin controller in add bus");
//		return "/admin/bus_register";
//	}
	
	@RequestMapping("/addBus")
	public ModelAndView addBuses(@ModelAttribute("bus") Bus bus,ModelAndView view) {
		log.info("In Admin controller in add bus");
		view.setViewName("/admin/bus_register");
		return view;
	}
	
//	@RequestMapping(path="/addNewBus",method=RequestMethod.POST)
//	public String addBus(@Valid @ModelAttribute("bus") Bus bus,Errors errors) {
//		log.info("Inside add new bus method");
//		if(errors.hasErrors()) {
//			log.info(errors);
//			return "/admin/bus_register";
//		}
//		else {
//		this.busService.addBus(bus);
//		return "/admin/homepage";
//		}
//	}
	
	@RequestMapping(path="/addNewBus",method=RequestMethod.POST)
	public ModelAndView addBus(@Valid @ModelAttribute("bus") Bus bus,Errors errors,ModelAndView view) {
		log.info("Inside add new bus method");
		if(errors.hasErrors()) {
			log.info(errors);
			view.setViewName("/admin/bus_register");
		}
		else {
			this.busService.addBus(bus);
			view.setViewName("/admin/homepage");
		}
		return view;
	}
	
	@RequestMapping("/buslist")
	public ModelAndView homeBusList(@ModelAttribute Bus bus,Model model,HttpServletRequest request,ModelAndView mView)
	{
		List<Bus> busList = this.busService.getAllBusDetails();
		model.addAttribute("bus", busList);
		//return "/admin/buslist";
		mView.setViewName("/admin/buslist");
		return mView;
	}
	
	
	@RequestMapping("/bus/{busId}")
	public ModelAndView busEdit(@PathVariable("busId") long busId ,Model model,ModelAndView moView) {
		Bus bus = this.busService.getBus(busId);
		log.info(bus);
		model.addAttribute("bus", bus);
		moView.setViewName("/admin/busDetails");
		return moView;
	}
	
	@RequestMapping("/deleteBus/{busId}")
	public ModelAndView busDelete(@PathVariable("busId") long busId ,Model model,ModelAndView moView) {
		log.info(busId);
		this.busService.removeBus(busId);
		moView.setViewName("redirect:/admin/buslist");
		return moView;
	}
	
	@RequestMapping("/bus/validateUpdateBus")
	public RedirectView upadteBus(@Valid @ModelAttribute("bus")Bus bus, Errors errors,HttpServletRequest request) {
		log.info("In update bus..........................");
		log.info("bus after update"+bus);
		if(errors.hasErrors()) {
			log.info(errors);
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl(request.getContextPath() + "/admin/bus/{busId}");
			return redirectView;
		}
		else {
			this.busService.updateBus(bus);
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl(request.getContextPath() + "/admin/buslist");
			return redirectView;
		}
	}
	/******************************************************************************************************************/
	
	//get bus list from db
	@GetMapping("/bus-list")
	public List<Bus> busList(@ModelAttribute Bus bus,Model model,HttpServletRequest request,ModelAndView mView)
	{
		List<Bus> busList = this.busService.getAllBusDetails();
		model.addAttribute("bus", busList);
		//return "/admin/buslist";
		mView.setViewName("/admin/buslist");
		//return mView;
		return busList;
	}
	
	//add buses
	@PostMapping("/bus-list")
	public Boolean addBus(@RequestBody Bus bus) {
		return busService.addBus(bus);
	}
	
	//get by id
	@GetMapping("/bus-list/{id}")
	public ResponseEntity<Bus> getBusById(@PathVariable Long id){
		Bus b = busService.getBus(id);
		if(b == null) {
			throw new ResourceNotFoundException("Bus not exist with id "+id);
		}
		return ResponseEntity.ok(b);
	}
	
	//updateBus
	@PutMapping("/bus-list/{busId}")
	public ResponseEntity<Bus> updateBus(@PathVariable Long busId, @RequestBody Bus bus){
		Bus b = busService.getBus(busId);
		if(b == null) {
			throw new ResourceNotFoundException("Bus not exist with id "+busId);
		}
//		b.setBusName(bus.getBusName());
		busService.updateBus(bus);
		return ResponseEntity.ok(bus);
	}
	
	//delete buses
	@DeleteMapping("/bus-list/{busId}")
	public ResponseEntity<Map<String, Boolean>> deleteBus(@PathVariable Long busId){
		Bus bus = busService.getBus(busId);
		if(bus == null) {
			throw new ResourceNotFoundException("Bus not exist with id "+busId);
		}
		busService.removeBus(busId);
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		result.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(result);
				
	}
	
	
	
}
