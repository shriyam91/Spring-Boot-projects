package com.luv2code.springdemo.cruddemo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.cruddemo.entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//in class jparepository<> we specify the class name which is annotated with @Table annotation ie it is used to save data to db and 2nd parameter
//is the type of the primary key and since the primary key in class employee is of type integer
}
