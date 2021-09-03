package com.root.Generic.AplicationLayer.JPA.Servicies;

import java.util.List;

import com.root.Generic.AplicationLayer.JPA.Models.Laboratorios;

public interface LaboratoriosService {

    
    public void createLab(Laboratorios item);
    public List<Laboratorios> getItems();
    public Laboratorios findLabById(long id);
    public Laboratorios updateLab(Laboratorios item, long l);
    public void deleteLabById(long id);
}