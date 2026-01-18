package com.EMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EMS.entities.Employee;
import com.EMS.services.EmployeeService;

@Controller
public class EmpController {
	@Autowired
	EmployeeService service;
	
	@GetMapping("/")
	public String Index() {
		return "Index";
	}
	@PostMapping("/save")
	public String RegisterEmployee(@ModelAttribute("emp") Employee emp) {
		service.registeremployee(emp);
		System.out.println("Employee Registered");
		return "Index";
	}
	
	@GetMapping("/ActiveEmployees")
	public String ActiveEmployees(Model m) {
	    m.addAttribute("employees", service.getAllActiveEmployees());
	    m.addAttribute("openModal", true); // 👈 ADD THIS
	    return "Index";
	}
	
	@GetMapping("/search")
	public String searchedEmployee(@RequestParam long id, Model m) {

	    Employee emp = service.getEmployeeById(id);

	    if (emp != null) {
	        m.addAttribute("employee", emp);
	    } else {
	        m.addAttribute("searchError", "Employee not found");
	    }

	    return "Index";
	}

	
	 @GetMapping("/delete/{id}")
	    public String deleteEmployee(@PathVariable Long id) {

	        service.deleteEmployeeById(id);

	        // Reload active employees
	        return "redirect:/ActiveEmployees";
	    }

	 
	 @PostMapping("/update")
	 public String updateEmployee(Employee emp) {
	     service.updateEmployee(emp);
	     return "redirect:/ActiveEmployees";
	 }

	

}
