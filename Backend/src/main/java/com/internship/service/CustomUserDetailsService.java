package com.internship.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.internship.model.Admin;
import com.internship.model.Employee;
import com.internship.repository.AdminRepo;
import com.internship.repository.EmployeeRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> admin=adminRepo.findByUserName(username);
		if(admin.isPresent()) {
			return new org.springframework.security.core.userdetails.User(
					admin.get().getUserName(),
					admin.get().getPassword(),
					List.of(new SimpleGrantedAuthority(admin.get().getRole()))
			);
		}
		
		Employee emp=employeeRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("username not found"));
		
		return new org.springframework.security.core.userdetails.User(
				emp.getUsername(),
				emp.getPassword(),
				List.of(new SimpleGrantedAuthority(emp.getRole()))
		);
	}
}
