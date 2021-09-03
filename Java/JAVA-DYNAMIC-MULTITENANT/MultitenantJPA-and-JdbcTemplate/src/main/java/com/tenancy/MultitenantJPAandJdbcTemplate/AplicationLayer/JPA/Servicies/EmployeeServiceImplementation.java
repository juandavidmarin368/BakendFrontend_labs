package com.tenancy.MultitenantJPAandJdbcTemplate.AplicationLayer.JPA.Servicies;

import java.util.List;
import java.util.Optional;

import com.tenancy.MultitenantJPAandJdbcTemplate.AplicationLayer.JPA.Models.Employee;
import com.tenancy.MultitenantJPAandJdbcTemplate.AplicationLayer.JPA.Repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImplementation implements EmployeeService{

    @Autowired
    EmployeeRepository em;

    @Override
    public void createEmployee(Employee item) {

        em.save(item);
    }

	@Override
	public List<Employee> getItems() {
		return null;
	}

	@Override
	public Optional<Employee> getEmployeeById(int id) {

       // Employee empl = ;

		return em.findById(id);
	}

}