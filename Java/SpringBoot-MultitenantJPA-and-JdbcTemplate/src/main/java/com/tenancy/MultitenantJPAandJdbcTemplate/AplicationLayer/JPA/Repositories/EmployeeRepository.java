package com.tenancy.MultitenantJPAandJdbcTemplate.AplicationLayer.JPA.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.tenancy.MultitenantJPAandJdbcTemplate.AplicationLayer.JPA.Models.Employee;


/**
 * Created by Java Developer Zone on 03-08-2017.
 */

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
   
}