package com.assignment.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.employee.exception.RecordNotFoundException;
import com.assignment.employee.model.Employee;
import com.assignment.employee.repository.EmployeeRepository;


@Service
public class EmployeeService{

	@Autowired
    private EmployeeRepository repository;

	public List<Employee> getAllEmployeesNameAndDobByDepartmentId(String departmentId) throws RecordNotFoundException {
		
		List<Employee> employeeList = repository.getAllEmployeesNameAndDobByDepartmentId(departmentId);
		
		if(employeeList.size()>0) {
            return employeeList;
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
	}

	public List<Employee> getAllEmployeesNamesHiredAfterDateAndMinSalaryIs(String particularDate, Integer minSalary)  throws Exception{
		
		List<Employee> employeeList = repository.getAllEmployeesNamesHiredAfterDateAndMinSalaryIs(particularDate, minSalary);
		
		if(employeeList.size()>0) {
            return employeeList;
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }

	}

	public Integer deleteEmployeeFromSalaryTblHavingHireDateBeforeGivenDate(String givenDate) throws RecordNotFoundException {
		
		Integer count = repository.searchEmployeeFromSalaryTblHavingHireDateBeforeGivenDate(givenDate);
        
        return count;
	}

	
}
