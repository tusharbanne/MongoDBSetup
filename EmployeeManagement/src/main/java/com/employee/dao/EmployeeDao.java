package com.employee.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

@Component
@Transactional
public class EmployeeDao {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public boolean addEmployees(List<Employee> emps) {
	
		try {
		employeeRepository.saveAll(emps);
		}
		catch(Exception sqlEx) {
			return false;
		}
		return true;
	}

	public List<Employee> getAllEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}

	public List<Employee> getEmployeesOfManager(Long managerId) {
		return employeeRepository.findByManagerId(managerId);
	}

	public Employee findEmployee(Long id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		return emp.get();
	}

	public void updateEmployee(Employee employeeWhoseMangerIsToBeChanged) {
		employeeRepository.save(employeeWhoseMangerIsToBeChanged);
	}
	
	
}
