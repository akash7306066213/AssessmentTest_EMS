package com.EMS.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EMS.entities.Employee;
import com.EMS.entities.Status;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	Optional<Employee> findByEmail(String email);
	
	List<Employee> findByStatus(Status status);

}
