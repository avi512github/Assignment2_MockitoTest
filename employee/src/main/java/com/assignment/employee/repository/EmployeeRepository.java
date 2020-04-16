package com.assignment.employee.repository;

import java.util.List;

import com.assignment.employee.model.Employee;

public interface EmployeeRepository {

	List<Employee> getAllEmployeesNameAndDobByDepartmentId(String departmentId);

	List<Employee> getAllEmployeesNamesHiredAfterDateAndMinSalaryIs(String particularDate, Integer minSalary);

	Integer searchEmployeeFromSalaryTblHavingHireDateBeforeGivenDate(String givenDate);

}
