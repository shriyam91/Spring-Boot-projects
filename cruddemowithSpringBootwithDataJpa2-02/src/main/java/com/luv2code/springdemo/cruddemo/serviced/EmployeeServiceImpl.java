package com.luv2code.springdemo.cruddemo.serviced;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.luv2code.springdemo.cruddemo.DAO.EmployeeRepository;
import com.luv2code.springdemo.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
	//since there are two classes implementing Employeedao interface' so we need to provide the bean of the implementing class which should be
	//run and if we do not provide the bean of the implementing class then error will come, we could have deleted one class but did not do so
	// bcz we want to give a idea to what to do when such situation comes, bcz in production level also there can be more than one implementing
	//classes
		 employeeRepository= theEmployeeRepository;
	}
	
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
		
	}

	
	
	@Override
	public Employee findById(int theId)  {
		
		Optional<Employee> theEmp= employeeRepository.findById(theId);
		if(theEmp.isPresent()) {
			
			return theEmp.get() ;
		}
		else  throw new RuntimeException("Employee with id "+theId+" is not present");
	}
	
	
	@Override
	
	public Employee save(Employee emp) {
		return employeeRepository.save(emp);

	}

	@Override
	
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);

	}

}
