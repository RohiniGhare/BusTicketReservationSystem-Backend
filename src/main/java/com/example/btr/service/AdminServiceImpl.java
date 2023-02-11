package com.example.btr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.btr.dto.AdminLogin;
import com.example.btr.exceptions.AdminNotFoundException;
import com.example.btr.model.Admin;
import com.example.btr.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public boolean addAdmin(Admin a) {
		Admin checkAdmin = this.adminRepository.findByEmailId(a.getEmailId());
		if(checkAdmin == null) {
			this.adminRepository.save(a);
			return true;
		}
		return false;
	}
	
	@Override
	public List<Admin> getAllAdminDetails() {
		return this.adminRepository.findAll();
	}
	
	@Override
	public Admin getAdmin(long adminId)throws AdminNotFoundException {
		Admin ad = adminRepository.findByAdminId(adminId);
		if(ad==null) {
			throw new AdminNotFoundException("");
		}
		return ad;
	}
	
	@Override
	public boolean updateAdmin(Admin admin) {
		//try {
			this.adminRepository.save(admin);
			return true;
		//}catch(DataAccessException d) {
		//	return false;
		//}
		
	}
	
	@Override
	public Admin validateAdmin(AdminLogin adminlogin) throws AdminNotFoundException {
		
		Admin admin = this.adminRepository.findByEmailId(adminlogin.getUsername());
		
		if(admin == null) {
			throw new AdminNotFoundException("In correct Credentials");
		}
		else {
			if(admin.getPassword().equals(adminlogin.getPassword())) {
				return admin;
			}
		}
		
		throw new AdminNotFoundException("In correct Credentials");
	}
	
	@Override
	public Admin validateChangePasswordAdmin(AdminLogin adminlogin) {
		
		Admin admin = this.adminRepository.findByEmailId(adminlogin.getUsername());
		
		if(admin == null) {
			return null;
		}
		else {
				return admin;
		}
		
	}
	
	@Override
	public void removeAdmin(long adminId) {
		Admin admin = this.adminRepository.findByAdminId(adminId);
		this.adminRepository.delete(admin);
	}
	
	@Override
	public boolean updateAdminPassword(AdminLogin adminLogin) {
		Admin admin =this.adminRepository.findByEmailId(adminLogin.getUsername());
		if(admin != null) {
			
			admin.setPassword(adminLogin.getPassword());
		
			this.adminRepository.save(admin);
			return true;
		}
		return false;
	}

	/*
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { // TODO Auto-generated method stub return null; }
	 */

}
