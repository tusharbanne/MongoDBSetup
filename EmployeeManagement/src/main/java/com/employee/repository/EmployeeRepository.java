package com.employee.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long>{

	@Query("select e from Employee e where e.employeeId = : mangerid UNION ALL from e join Employee m on e.managerId=m.employeeId")
	public List<Employee> fetchSalaryOfAllSubordinates(@Param("mangerid")String managerId);
 
}
