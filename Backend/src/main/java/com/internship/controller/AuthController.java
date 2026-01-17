package com.internship.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internship.model.DTO;
import com.internship.model.LoginResponse;
import com.internship.service.AuthService;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody DTO loginRequest){
		return ResponseEntity.ok(
				authService.login(loginRequest.getUsername(),
						loginRequest.getPassword())
		);
		
	}
}
