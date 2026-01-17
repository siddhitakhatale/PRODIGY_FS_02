package com.internship.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internship.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

	Optional<Employee> findByUsername(String username);

	Optional<Employee> findByEmail(String email);

}
