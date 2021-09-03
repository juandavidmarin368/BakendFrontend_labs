package com.root.Generic.AplicationLayer.JPA.Servicies;

import java.util.List;

import javax.transaction.Transactional;

import com.root.Generic.AplicationLayer.JPA.Models.Laboratorios;
import com.root.Generic.AplicationLayer.JPA.Repositories.LaboratoriosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class LaboratoriosServiceImplementation implements LaboratoriosService {

    @Autowired
    LaboratoriosRepository labRepo;

    @Override
    public void createLab(Laboratorios item) {

    }

    @Override
    public List<Laboratorios> getItems() {
        return (List<Laboratorios>) labRepo.findAll();
    }

    @Override
    public Laboratorios findLabById(long id) {
        return null;
    }

    @Override
    public Laboratorios updateLab(Laboratorios item, long l) {
        return null;
    }

    @Override
    public void deleteLabById(long id) {

    }

    
}