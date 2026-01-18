package com.EMS.services;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EMS.entities.Employee;
import com.EMS.entities.Status;
import com.EMS.repository.EmployeeRepository;
@Service
public class EmployeeService {
    @Autowired
	EmployeeRepository repository;
	 
	public void registeremployee(Employee emp) {
		// TODO Auto-generated method stub
		repository.save(emp);
	}

	public List<Employee> getAllActiveEmployees() {
		// TODO Auto-generated method stub
		
		
		return repository.findByStatus(Status.ACTIVE);
	}

	public void deleteEmployeeById(Long id) {
		// TODO Auto-generated method stub
		
		Employee emp=repository.findById(id).orElseThrow(()->new RuntimeException("Employee Not Found"));
		emp.setStatus(Status.INACTIVE);
		repository.save(emp);
	}

	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		return repository.getById(id);
	}

	public void updateEmployee(Employee emp) {
	    Employee existing = repository.findById(emp.getId()).orElseThrow();

	    existing.setName(emp.getName());
	    existing.setEmail(emp.getEmail());
	    existing.setDepartment(emp.getDepartment());
	    existing.setSalary(emp.getSalary());

	    repository.save(existing);
	}

	
	

}
