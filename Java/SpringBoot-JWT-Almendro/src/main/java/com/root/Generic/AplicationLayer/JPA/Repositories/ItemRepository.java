package com.root.Generic.AplicationLayer.JPA.Repositories;

import com.root.Generic.AplicationLayer.JPA.Models.Item;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long>{

    
}