package com.example.btr.service;

import java.util.List;

//import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.btr.dto.AdminLogin;
import com.example.btr.exceptions.AdminNotFoundException;
import com.example.btr.model.Admin;

public interface AdminService{ //extends UserDetailsService {
	
	public boolean addAdmin(Admin a);
	public List<Admin> getAllAdminDetails();
	public Admin getAdmin(long adminId) throws AdminNotFoundException;
	public boolean updateAdmin(Admin admin);
	public boolean updateAdminPassword(AdminLogin adminLogin);
	public Admin validateAdmin(AdminLogin adminlogin) throws AdminNotFoundException;
	public void removeAdmin(long adminId);
	public Admin validateChangePasswordAdmin(AdminLogin adminlogin);

}
