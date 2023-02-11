package com.example.btr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RestController
public class MainController {
	
	@RequestMapping("/adminLanding")
	public String indexAdmin() {
//		log.info("Demo Logger");
		return "index_admin";
	}
	
	@RequestMapping("/customerLanding")
	public String indexCustomer() {
//		log.info("Demo Logger");
		return "index_customer";
	}

}
