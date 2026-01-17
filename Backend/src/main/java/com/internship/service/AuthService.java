package com.internship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.internship.config.JwtUtil;
import com.internship.model.Employee;
import com.internship.model.LoginResponse;
import com.internship.repository.EmployeeRepo;

@Service
public class AuthService {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	public LoginResponse login(String username,String password) {
		Authentication authentication=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password)
		);
		String role=authentication.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
		
		boolean firstLogin=false;
		String nextAction="LOGIN";
		if(role.equals("EMPLOYEE")) {
			Employee emp=employeeRepo.findByUsername(username)
					.orElseThrow(() -> new RuntimeException("Employee not found"));
			firstLogin=emp.isFirstLogin();
			if(firstLogin) {
				nextAction="SET_PASSWORD";
				
			}
		}
		String token=jwtUtil.generateToken(username,"ROLE_"+ role);
		return new LoginResponse(token,role,firstLogin,nextAction);
	}

}
