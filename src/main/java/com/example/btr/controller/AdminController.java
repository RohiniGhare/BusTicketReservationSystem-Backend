package com.example.btr.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.btr.dto.AdminLogin;
import com.example.btr.exceptions.AdminAlreadyExistException;
import com.example.btr.exceptions.AdminNotFoundException;
import com.example.btr.model.Admin;
import com.example.btr.service.AdminService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	public Logger log = Logger.getLogger(AdminController.class);
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/register")
	public ModelAndView registrationAdmin(@ModelAttribute("admin")Admin admin,ModelAndView view) {
		/*log.info("admin in register");*/
		/*log.info(admin);*/
		view.setViewName("/admin/register");
		return view;
	}
	
	@RequestMapping("/homepage")
	public ModelAndView homeAdmin(Model model,ModelAndView view)
	{
		List<Admin> admins = adminService.getAllAdminDetails();
		model.addAttribute("admins", admins);
		view.setViewName("/admin/homepage");
		return view;
	}
		
	@RequestMapping(path="/processForm", method=RequestMethod.POST)
	public ModelAndView handelRegisterFormAdmin(HttpSession session,HttpServletRequest request,ModelAndView view ,@Valid @ModelAttribute("admin") Admin admin,BindingResult result) {
		log.info(admin);
		if(result.hasErrors()) {
			log.info(result);
			view.setViewName("/admin/register");
		}
		else {
			if(this.adminService.addAdmin(admin)) {
				log.info("Admin added");
				session = request.getSession();
				session.setAttribute("adminId", admin.getAdminId());
				view.setViewName("index_admin");
			}else {
				session.setAttribute("invalidRegister", "Admin already exist !!");
				log.info("Admin already exist !!!");
				view.setViewName("/admin/register");
			}
		}
		return view;
	}
	
	@RequestMapping("/login")
	public ModelAndView adminLoginPage(ModelAndView view,HttpSession session, @ModelAttribute("adminlogin") AdminLogin adminlogin) {
		log.info("Reached the login page.");
		session.removeAttribute("invalidLogin");
		view.setViewName("/admin/login");
		return view;
	}
	
	@RequestMapping(path="/loginValidate",method=RequestMethod.POST)
	public ModelAndView loginAdmin(ModelAndView view,HttpSession session ,HttpServletRequest request,@Valid @ModelAttribute("adminlogin") AdminLogin adminlogin,Errors error) throws AdminNotFoundException {
		Admin admin = this.adminService.validateAdmin(adminlogin);
		
		if(admin != null) {
			session = request.getSession();
			session.setAttribute("adminId", admin.getAdminId());
			log.info("AdminId : "+admin.getAdminId());
			view.setViewName("redirect:/admin/homepage");
		}
		else {
			session.setAttribute("invalidLogin", "Invalid Admin Credentials !!");
			view.setViewName("/admin/login");
		}
		return view;
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public RedirectView logoutAdmin(HttpServletRequest request) {
		log.info("In logout");
		HttpSession session =request.getSession();
		session.invalidate();
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/admin/login");
		return redirectView;
	}
		
	@RequestMapping(value="/profile/delete-Admin/{adminId}", method = RequestMethod.POST)
	public ModelAndView deleteAdmin(@PathVariable("adminId") long adminId,HttpServletRequest request, ModelAndView view)
	{
		log.info("In the delete method");
		log.info("adminId : "+adminId);
		this.adminService.removeAdmin(adminId);
		view.setViewName("/index_admin");
		return view;
	}
	
	@RequestMapping("/profile/{adminId}")
	public ModelAndView adminProfile(@PathVariable("adminId") long adminId ,Model model,ModelAndView moView) {
		Admin admin;
		try {
			admin = this.adminService.getAdmin(adminId);
			log.info(admin);
			model.addAttribute("admin", admin);
			moView.setViewName("/admin/profileAdmin");
			return moView;
		} catch (AdminNotFoundException e) {
			model.addAttribute("errMsg", "Admin not found to update it");
			moView.setViewName("errorPage");
			return moView;			
		}
	}

	@RequestMapping("/profile/validateUpdateAdmin/{adminId}")
	public ModelAndView upadteAdmin(@Valid @ModelAttribute("admin")Admin admin, Errors errors,Model model,ModelAndView view) {
		log.info("In validate admin");
		log.info("admin after update"+admin);
		if(errors.hasErrors()) {
			log.info(errors);
			view.setViewName("/profile/{adminId}");
		}
		else {
			if(this.adminService.updateAdmin(admin)) {
				view.setViewName("redirect:/admin/homepage");
			}else {
				model.addAttribute("errMsg", "Admin data Update failed try after sometime");
				view.setViewName("redirect:/errorPage");
			}
		}
		return view;
		
	}
		
	@RequestMapping("/forgotPassword")
	public ModelAndView adminforgotPassword(HttpSession session, @ModelAttribute("adminlogin") AdminLogin adminlogin, ModelAndView view) {
		log.info("Reached the forgot password page.");
		session.removeAttribute("invalidLogin");
		view.setViewName("/admin/forgotPassword");
		return view;
	}
	
	@RequestMapping(path="/changePassword",method=RequestMethod.POST)
	public ModelAndView changePasswordAdmin(HttpSession session ,HttpServletRequest request,@Valid @ModelAttribute("adminlogin") AdminLogin adminlogin,Errors error,ModelAndView view) {
		Admin admin = this.adminService.validateChangePasswordAdmin(adminlogin);
		
		if(admin != null) {
			this.adminService.updateAdminPassword(adminlogin);
			view.setViewName("redirect:/admin/login");
		}
		else {
			session.setAttribute("invalidLogin", "Invalid Admin Credentials !!");
			view.setViewName("/admin/forgotPassword");
		}
		return view;
	}
	
	/*****************************************************************************************************************************/

	@PostMapping("/add-admin")
	public ResponseEntity<Boolean> registerAdmin(@RequestBody Admin admin) throws AdminAlreadyExistException{
		boolean result = adminService.addAdmin(admin);
		if(result == false) {
			throw new AdminAlreadyExistException("Admin with this mail id already exists !!!!");
		}
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/login-admin")
	public ResponseEntity<?> loginAdmin(@RequestBody Admin admin) throws AdminNotFoundException{
		AdminLogin adminLogin = new AdminLogin();
		adminLogin.setUsername(admin.getEmailId());
		adminLogin.setPassword(admin.getPassword());
		
		System.err.println("email "+adminLogin.getUsername());
		System.err.println("email "+adminLogin.getPassword());
		
		Admin a = this.adminService.validateAdmin(adminLogin);
		
		if(a != null) {
			log.info("AdminId : "+a.getAdminId());
			return ResponseEntity.ok(a);
		}
		else {
			log.info("Invalid Admin Credentials !!");
			return (ResponseEntity<?>) ResponseEntity.internalServerError();
		}
	}
	
	@PutMapping("/update-password")
	public ResponseEntity<Admin> updatePassword(@RequestBody Admin admin){
		AdminLogin adminLogin = new AdminLogin();
		adminLogin.setUsername(admin.getEmailId());
		adminLogin.setPassword(admin.getPassword());
		boolean result = this.adminService.updateAdminPassword(adminLogin);
		if(result == true)
			return ResponseEntity.ok(admin);
		else
			return ResponseEntity.ok(null);
		
	}

}
