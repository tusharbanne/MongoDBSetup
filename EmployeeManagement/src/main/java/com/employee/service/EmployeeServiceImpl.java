package com.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dao.EmployeeDao;
import com.employee.dto.EmployeeDetailsDto;
import com.employee.entity.Employee;

@Service
public class EmployeeServiceImpl {

	@Autowired
	EmployeeDao employeeDao;
	
	Long salary = 0l;
	
	public String addNewEmployee(List<EmployeeDetailsDto> emps) {
		
		List<Employee> employees = new ArrayList<>();
		emps.forEach(e -> employees.add(new Employee(e.getEmployeeId(), e.getFirstName(), e.getLastName(), e.getCityName(), e.getSalary(), e.getManagerId())));
		
		boolean isSaveSuccessfull = employeeDao.addEmployees(employees);
		
		if (isSaveSuccessfull) {
			return "Success";
		}
		else {
			return "Save Failed";
		}
		
	}
	
	public List<EmployeeDetailsDto> getEmployees(){
		
		List<Employee> emps = employeeDao.getAllEmployees();
		List<EmployeeDetailsDto> empDetails = new ArrayList<>();
		emps.forEach(e -> empDetails.add(new EmployeeDetailsDto(e.getEmployeeId(), e.getFirstName(), e.getLastName(),
				e.getCityName(), e.getSalary(), e.getManagerId())));
	return empDetails;
	}

	public void computeTotalSalaryOfsubordinates(String managerId) {
		
		// make a recursive call
		salary = 0l;
		Long finalSalary = calculateTotalSalary(managerId);
	}

	private Long calculateTotalSalary(String managerId) {
		
		List<Employee> emps = employeeDao.getEmployeesOfManager(managerId);
		
		emps.getAll
		
		emps.
		
	}
	
}
