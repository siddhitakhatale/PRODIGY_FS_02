package com.internship.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String name;
	
	@Email
	@NotBlank
	@Column(unique = true)
	private String email;
	
	@NotBlank
	@Column(unique = true)
	private String phone;
	
	@NotBlank
    @Column(unique = true, nullable = false,name = "username")
    private String username;
	
	private long salary;
	
	private String deparment;
	
	@NotBlank
	private String address;
	
	@NotBlank
	private String password;
	
	private boolean firstLogin=true;
	
	private String role="ROLE_EMPLOYEE";
	
	public Employee() {
		
	}

	public Employee(long id, @NotBlank String name, @Email @NotBlank String email, @NotBlank String phone,
			@NotBlank String userName, long salary, String deparment, @NotBlank String address,
			@NotBlank String password, boolean firstLogin, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.username = userName;
		this.salary = salary;
		this.deparment = deparment;
		this.address = address;
		this.password = password;
		this.firstLogin = firstLogin;
		this.role = role;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getDeparment() {
		return deparment;
	}

	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public boolean isFirstLogin() {
		return firstLogin;
	}



	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}



	
	
	
}
