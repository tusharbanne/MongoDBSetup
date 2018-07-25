package com.employee.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long>{

	public List<Employee> findByManagerId(Long managerId);
 
}
