package com.luv2code.springdemo.cruddemo.serviced;

import java.util.List;

import com.luv2code.springdemo.cruddemo.entity.Employee;

public interface EmployeeService {
	
public List<Employee> findAll();
	
	public Employee findById(int theId) ;
	
	public Employee save(Employee emp);
	public void deleteById(int theId);

}
