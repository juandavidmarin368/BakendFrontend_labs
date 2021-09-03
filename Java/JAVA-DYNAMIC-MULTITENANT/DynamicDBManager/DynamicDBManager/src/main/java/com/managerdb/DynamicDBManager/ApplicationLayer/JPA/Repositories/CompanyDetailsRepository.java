package com.managerdb.DynamicDBManager.ApplicationLayer.JPA.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.managerdb.DynamicDBManager.ApplicationLayer.JPA.Models.CompanyDetails;


public interface CompanyDetailsRepository extends CrudRepository<CompanyDetails,Integer> {
   
}