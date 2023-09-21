package com.luv2code.springdemo.cruddemo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.cruddemo.entity.Employee;


//@Repository, we commented out this annotation bcz we are going to add annotation @RespositoryRestResource(path=..), we use this annotation
//when we have to change the bydefault endpoint created for the entity class like for Employee it is employees , for Customer it will be customers
//where while creating the endpoint the logic is to make the first letter small and add a 's' at the last, So if we want to change the endpoint
//then we use the annotation @RespositoryRestResource(path="newendpointname")
@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//in class jparepository<> we specify the class name which is annotated with @Table annotation ie it is used to save data to db and 2nd parameter
//is the type of the primary key and since the primary key in class employee is of type integer
}
