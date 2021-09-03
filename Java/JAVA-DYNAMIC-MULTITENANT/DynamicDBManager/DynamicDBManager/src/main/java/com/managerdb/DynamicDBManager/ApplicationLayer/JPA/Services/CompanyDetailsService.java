package com.managerdb.DynamicDBManager.ApplicationLayer.JPA.Services;

import java.util.List;
import java.util.Optional;

import com.managerdb.DynamicDBManager.ApplicationLayer.JPA.Models.CompanyDetails;


public interface CompanyDetailsService{

    public void createCompany(CompanyDetails company);

}