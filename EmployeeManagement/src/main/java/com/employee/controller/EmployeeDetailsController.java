package com.employee.controller;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.dto.EmployeeDetailsDto;
import com.employee.service.EmployeeServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@Produces({MediaType.APPLICATION_JSON})
@Controller
public class EmployeeDetailsController {

	@Autowired
	EmployeeServiceImpl empServiceImpl;
	
	@RequestMapping(value="/maximumDirectSubordinates", method=RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value= {@ApiResponse(code=200, message="Success", response=EmployeeDetailsDto.class)})
	public String getMaximumDirectSubordinates() {
		return "done";
	}

	@RequestMapping(value="/changeManager", method=RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value= {@ApiResponse(code=200, message="Success")})
	public String changeManager(@RequestParam(value = "managerId") String managerId,
			@RequestParam(value = "employeeId") String employeeId) {
		return "done";
	}

	@RequestMapping(value="/calculateTotalSalaryOfAllSubordinatesOfEmployee", method=RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value= {@ApiResponse(code=200, message="Success")})
	public String calculateTotalSalaryOfAllSubordinatesOfEmployee(@RequestParam(value = "managerId") String managerId) {
		return "done";
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponses(value= {@ApiResponse(code=200, message="Success")})
	public List<EmployeeDetailsDto> getEmployees() {
		return empServiceImpl.getEmployees();
	}
	
	@Consumes({MediaType.APPLICATION_JSON})
	@RequestMapping(value="/addEmployees", method=RequestMethod.POST)
	@ApiResponses(value= {@ApiResponse(code=200, message="Success")})
	public String addNewEmployee(@RequestBody List<EmployeeDetailsDto> employees) {
		empServiceImpl.addNewEmployee(employees);
		return "done";
	}

}
