package com.internship.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.internship.model.Admin;
import com.internship.model.Employee;

import com.internship.repository.AdminRepo;
import com.internship.repository.EmployeeRepo;

@Service
public class AdminService {
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Employee addEmployee(Employee employee) {
		employee.setPassword(passwordEncoder.encode("welcome@123"));
		employee.setFirstLogin(true);
		employee.setRole("ROLE_EMPLOYEE");
		return employeeRepo.save(employee);
	}

	public List<Employee> getAll() {
		return employeeRepo.findAll();
	}

	public Employee updateOne(Employee employee) {
		
		if(employee.getPassword()!=null && !employee.getPassword().startsWith("$2a$")) {
			employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		}
		return employeeRepo.save(employee);
	}

	public void deleteOne(Long id) {
		employeeRepo.deleteById(id);
	}

	public Employee getOne(Long id) {
		return employeeRepo.findById(id).orElse(new Employee());
	}

	public Optional<Employee> getEmployeeByUsername(String username) {
		return employeeRepo.findByUsername(username);
	}

	public Admin registerAdmin(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		admin.setRole("ROLE_ADMIN");
		return adminRepo.save(admin);
	}

	public Employee update(Long id, Employee employee) {
		
		Employee emp=employeeRepo.findById(id).orElseThrow(() ->
		   new RuntimeException("employee not found"));
		emp.setName(employee.getName());
		emp.setEmail(employee.getEmail());
		emp.setPhone(employee.getPhone());
		emp.setDeparment(employee.getDeparment());
		emp.setUsername(employee.getUsername());
		emp.setSalary(employee.getSalary());
		emp.setAddress(employee.getAddress());
		emp.setPassword(emp.getPassword());
		return employeeRepo.save(emp);
	}

	

}
