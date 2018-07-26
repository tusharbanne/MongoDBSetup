package com.employee.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.EmployeeDetailsDto;
import com.employee.service.EmployeeServiceImpl;

import io.swagger.annotations.Api;

@Api
@RestController
public class EmployeeDetailsController {

	@Autowired
	EmployeeServiceImpl empServiceImpl;
	
	@RequestMapping(value="/maximumDirectSubordinates", method=RequestMethod.GET)
	public Long getMaximumDirectSubordinates() {
		return empServiceImpl.getEmployeeWithMaximumDirecteSubordinates();
	}

	@RequestMapping(value="/changeManager", method=RequestMethod.GET)
	public String changeManager(@RequestParam(value = "managerId") Long managerId,
			@RequestParam(value = "employeeId") Long employeeId) {
		return empServiceImpl.changeMangerOfEmployee(managerId, employeeId);
	}

	@RequestMapping(value="/calculateTotalSalaryOfAllSubordinatesOfEmployee", method=RequestMethod.GET)
	public Long calculateTotalSalaryOfAllSubordinatesOfEmployee(@RequestParam(value = "managerId") Long managerId) {
		return empServiceImpl.computeTotalSalaryOfsubordinates(managerId);
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public List<EmployeeDetailsDto> getEmployees() {
		return empServiceImpl.getEmployees();
	}
	
	@RequestMapping(value="/addEmployees", method=RequestMethod.POST)
	public String addNewEmployee(@RequestBody List<EmployeeDetailsDto> employees) {
		empServiceImpl.addNewEmployee(employees);
		return "done";
	}

}
