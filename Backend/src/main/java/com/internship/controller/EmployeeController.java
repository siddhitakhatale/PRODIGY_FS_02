package com.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internship.model.Employee;
import com.internship.model.SetPasswordRequest;
import com.internship.service.AdminService;


@RestController
@RequestMapping("/employee")

public class EmployeeController {
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/set-password")
	public ResponseEntity<String> setPassword(@RequestBody SetPasswordRequest setPasswordRequest,Authentication authentication){
		 String username = authentication.getName();
	      System.out.println("Username from JWT in setPassword: " + username);
		
		Employee emp=adminService.getEmployeeByUsername(username)
				.orElseThrow(() -> new RuntimeException("employee not found"));
		if(!emp.isFirstLogin()) {
			return ResponseEntity.badRequest().body("password aldready set");
		}
		emp.setPassword(setPasswordRequest.getPassword());
		
		emp.setFirstLogin(false);
		adminService.updateOne(emp);
		return ResponseEntity.ok("password set successfully");
	}
	@PreAuthorize("hasRole('EMPLOYEE')")
	@GetMapping("/me")
	public ResponseEntity<Employee> getMyProfile(Authentication authentication){
		String username=authentication.getName();
		Employee employee=adminService.getEmployeeByUsername(username).orElseThrow(()-> new RuntimeException("employee not found"));
		return ResponseEntity.ok(employee);
	}
	
}
