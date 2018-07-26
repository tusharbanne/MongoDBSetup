package com.employee.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.constants.StringConstants;
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

	public Long computeTotalSalaryOfsubordinates(Long managerId) {
		
		// make a recursive call
		salary = 0l;
		return calculateTotalSalary(managerId);
		
	}

	private Long calculateTotalSalary(Long managerId) {
		
		List<Employee> emps = employeeDao.getEmployeesOfManager(managerId);
		
		if(!emps.isEmpty()) {
			emps.forEach(e ->{
				calculateTotalSalary(e.getEmployeeId());
				salary = salary + e.getSalary();	
			});
		}
		return salary;
		
	}

	
	
	public String changeMangerOfEmployee(Long managerId, Long employeeId) {

		// CHECK IF MANAGERID IS VALID
		Employee manager = employeeDao.findEmployee(managerId);

		if (manager != null) {
			Map<Long, Long> managerEmployee = new HashMap<>();
			boolean isCyclicReference = false;

			// loop unitl the end is found. End is defined by managerId as 0.
			while (manager.getManagerId() != 0) {
				if (!(manager.getManagerId() == employeeId)) {
					manager = employeeDao.findEmployee(managerId);
				} else {
					isCyclicReference = true;
					break;
				}
			}

			// To avoid cyclic pattern of employee manager combination.
			if (!isCyclicReference) {
				Employee employeeWhoseMangerIsToBeChanged = employeeDao.findEmployee(employeeId);
				employeeWhoseMangerIsToBeChanged.setManagerId(managerId);
				employeeDao.updateEmployee(employeeWhoseMangerIsToBeChanged);
				return StringConstants.MANAGER_CHANGED;
			} else {
				return StringConstants.INVALID_MANAGER_EMPLOYEE_COMBINATION;
			}
		} else {
			return StringConstants.INVALID_MANAGER;
		}
	}

	public Long getEmployeeWithMaximumDirecteSubordinates() {

		List<Employee> emps = employeeDao.getAllEmployees();
		
		Map<Long, Long> subordinateToManager = new HashMap<>();
		
		emps.forEach(e -> subordinateToManager.put(e.getEmployeeId(), e.getManagerId()));
		
		Map<Long, List<Long>> managerToEmployeeCount = new HashMap<>();
		
		for (Map.Entry<Long, Long> entry : subordinateToManager.entrySet()) {
			Long empId = entry.getKey();
			Long managerId = entry.getValue();

			if (!empId.equals(managerId)) {
				List<Long> directReportList = managerToEmployeeCount.get(managerId);
				if (directReportList == null)
					directReportList = new ArrayList<Long>();
				directReportList.add(empId);
				managerToEmployeeCount.put(managerId, directReportList);

			}
		}
		
		int count = 0;
		Long managerId = 0l;
		
		for (Map.Entry<Long, List<Long>> entry : managerToEmployeeCount.entrySet()) {
			
			if(entry.getValue().size() > count) {
				managerId = entry.getKey();
				count = entry.getValue().size();
			}
			
		}
		
		return managerId;
	}
	
	
	
	
	
}
