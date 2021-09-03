package com.tenancy.MultitenantJPAandJdbcTemplate.AplicationLayer.JPA.Servicies;

import java.util.List;
import java.util.Optional;

import com.tenancy.MultitenantJPAandJdbcTemplate.AplicationLayer.JPA.Models.Employee;


public interface EmployeeService{

    public void createEmployee(Employee item);
    public List<Employee> getItems();
    public Optional<Employee> getEmployeeById(int id);

}