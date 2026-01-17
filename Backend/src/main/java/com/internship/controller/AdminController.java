package com.internship.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internship.model.Admin;
import com.internship.model.Employee;
import com.internship.service.AdminService;

@RestController
@CrossOrigin
@RequestMapping("/admin/employees")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@PostMapping()
	public ResponseEntity<Employee>register(@RequestBody Employee employee) {
		return ResponseEntity.ok(adminService.addEmployee(employee));
	}
	
	@GetMapping()
	public ResponseEntity<List<Employee>> getAll(){
		return ResponseEntity.ok(adminService.getAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Employee> findOne(@PathVariable Long id){
		return ResponseEntity.ok(adminService.getOne(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateOne(@PathVariable Long id,@RequestBody Employee employee){
		employee.setId(id);
		return ResponseEntity.ok(adminService.update(id,employee));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,String>> deleteOne(@PathVariable Long id){
		adminService.deleteOne(id);
		return ResponseEntity.ok(Map.of("message","deleted successfully"));
	}
}
