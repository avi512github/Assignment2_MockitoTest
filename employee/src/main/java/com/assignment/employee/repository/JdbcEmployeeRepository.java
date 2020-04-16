package com.assignment.employee.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.assignment.employee.model.Employee;

@Repository
public class JdbcEmployeeRepository implements EmployeeRepository{

	
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 
	 private static final String DATE_FORMAT = "YYYY-MM-DD";


	@Override
	public List<Employee> getAllEmployeesNameAndDobByDepartmentId(String departmentId) {
		
		return jdbcTemplate.query(
                "select emp.first_name, emp.last_name, emp.birth_date from employees emp, dept_emp dep  where emp.emp_no=dep.emp_no and dep.dept_no=?",
                new Object[]{departmentId},
                (rs, rowNum) ->
                        new Employee(
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getTimestamp("birth_date")
                        )
        );
	}

	@Override
	public List<Employee> getAllEmployeesNamesHiredAfterDateAndMinSalaryIs(String particularDate, Integer minSalary) {
		
		return jdbcTemplate.query(
                "select emp.emp_no, emp.first_name, emp.last_name, emp.birth_date, emp.hire_date from employees emp, salaries sal  where emp.emp_no=sal.emp_no and emp.hire_date > TO_DATE(?, 'YYYY-MM-DD') and sal.salary >= ?",
                new Object[]{particularDate + "%", minSalary},
                (rs, rowNum) ->
                        new Employee(
                        		rs.getInt("emp_no"),
                        		rs.getTimestamp("birth_date"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getTimestamp("hire_date")
                        )
        );
	}

	@Override
	public Integer searchEmployeeFromSalaryTblHavingHireDateBeforeGivenDate(String givenDate) {
		
		Integer count =  jdbcTemplate.update(
                "delete from salaries where emp_no in(select emp_no from employees where hire_date < TO_DATE(?, 'YYYY-MM-DD'))",
                new Object[]{givenDate}
        );
		
		return count;
	}
	 
	 
}
