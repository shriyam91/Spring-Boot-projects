package com.luv2code.springdemo.cruddemo.restController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luv2code.springdemo.cruddemo.entity.Employee;
import com.luv2code.springdemo.cruddemo.serviced.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService empService;
	
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		empService=employeeService;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return empService.findAll();
	}
	
	@GetMapping("/employees/{intId}")
	public EntityModel<Employee> findById(@PathVariable int intId){
		Employee emp= empService.findById(intId);
		
		 EntityModel<Employee> entityModel= EntityModel.of(emp);
			//webmvcbuilder is to ease building links instance pointing to spring mvc controllers
		//so since we wish to add a link ie www.localhost:8080/employees so here we have to add a link to one of the above method ie findAll()
				 WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).findAll());
		 		entityModel.add(link.withRel("All-EmployeeList"));
				return entityModel;
	}
	
	
	@PostMapping("/employees")
	public  EntityModel<Employee> addEmployee(@RequestBody Employee emp){
		emp.setId(0);
		Employee savedEmp= empService.save(emp); 
//		note: it is a good practice to return response status, and to return response status we use ResponseEntity class
		//since it is a post mapping so we want to return the status of the created entry so we will use the created method of the responseentity
				//and note that inside created method we need to pass the uri location of the new employee that is created ex new employee has the id 4 
			// so the urilocation will be localhost:8080/employees/4 , and returning the uri also provides more usablility to the user bcz the user of the
			//api can then directly click on that uri and check the entry
//So the urilocation var should contain something like localhost:8080/employees/employeeid
EntityModel<Employee> entityModel= EntityModel.of(savedEmp);  
		WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).findById(savedEmp.getId()));
		entityModel.add(link.withRel("Link-to-the-saved-Employee"));
		return entityModel;
	//Note: Here in this project we have created link using webmvclinkbuilder
		
//		URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest()//these 2 methods together will give the current url ie localhost:8080/employees
//	.path("/{id}")//this will add a new path ie '/id' to the current path
//	.buildAndExpand(savedEmp.getId()).toUri();//through this method we define what will be the value of the pathvariable ie id which
//		//is being added as the extended path and then we convert this whole to uri usint toUri() method
//		
//return ResponseEntity.created(uriLocation).build();//this will return the status of created entry  ie 201 alongwith the url of the newly created
		//entry in the header section

	}
	
	@PutMapping("/employees")
	public void updateEmployee(@RequestBody Employee emp){
		
		empService.save(emp); 
	}
	
	@DeleteMapping("/employees/{empId}")
	public String deleteEmployee(@PathVariable int empId){
		
		empService.deleteById(empId);
		
		return "Employee with id "+empId +" has been deleted";
	}
}
