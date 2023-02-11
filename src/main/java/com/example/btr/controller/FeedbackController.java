package com.example.btr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FeedbackController {
	
	@RequestMapping("/feedback")
	public String feedbackpage()
	{
		return "feedback";
	}
}