package com.internship.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internship.model.Admin;
import com.internship.model.Employee;


@Repository
public interface AdminRepo extends JpaRepository<Admin, Long>{

	

	Optional<Admin> findByUserName(String username);

}
