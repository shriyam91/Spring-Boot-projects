package com.luv2code.springdemo.cruddemo.restController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
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
		List<Employee> el= empService.findAll();
		return el;
		//return empService.findAll();
	}
	
	//write one more getMapping method to demonstrate dynamic filtering
		@GetMapping("/employees-with-dynamic-filtering")
		public MappingJacksonValue findAllDynamicFiltering(){
			List empList=empService.findAll();
			//Employee empobj = new Employee();
	//steps to create a dynamic filter:
	//since it is a dynamic filter so we write code for this only inside the api we want like for ex we want to not display the email when returning
	//the complete list of employees but elsewhere it can be displayed for ex in api of geting a single employee data it can be displayed
	//so inside the method written to return all employees 1st:wrap the object(here it is a list) inside the MappingJacksonValue class
	//2:use setFilters()method of mappingjacksonvalue class to set a filter
	//3:Now define the filter(ie 'filters' here) using SimpleFilterProvider class where we use it's addFilter("..", ...)method to give the name
	//of the filter and the variable used for the filter
	//4:Now we define the task of the filter using SimpleBeanPropertyFilter class and here we have used it's filterOutAllExcept(....)method which
	//will include only those fields in the json response which will be mentioned inside this function and else all will be excluded
	//note that:we use SimpleFilterProvider to just define the name and add a new filter and then we use the SimpleBeanPropertyFiler class to
	//define the task of the filter ie we use it's method to exclude the fields that we do not want to show in the json reponse
	//Also note that: the name of the filter that is added using SimpleFilterProvider  has to be mentioned above the Employee class(or on the
	//entity class whose fields are to be excluded) using @JsonFilter(nameofthefilter)
			MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(empList);
			SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName","lastName");
			SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("filterforEmployeedata", filter);//the name of the filter ie filterforEmployeedata
	//has to to be also written in above the Employee class using '@JsonFilter("filterforEmployeedata") ,note that both name should be same
			mappingJacksonValue.setFilters(filters);
			return mappingJacksonValue;  
		}
	
	@GetMapping("/employees/{intId}")
	public EntityModel<Employee> findById(@PathVariable int intId){
		if( intId == 0 || intId > empService.findAll().size()) {
			throw new CustomerNotFoundException("Customer with id "+intId+ " does not exist");
		}
		
		
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
