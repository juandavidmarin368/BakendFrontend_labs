package com.root.Generic.AplicationLayer.JPA.Servicies;

import java.util.List;

import com.root.Generic.AplicationLayer.JPA.Models.Item;
import com.root.Generic.AplicationLayer.JPA.Repositories.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemServiceImplementation implements ItemService {

    @Autowired
    ItemRepository itemRepository;


    @Override
    public void createItem(Item item) {

        itemRepository.save(item);
    }

    @Override
    public List<Item> getItems() {
        return null;
    }

    @Override
    public Item findItemsById(long id) {
        return null;
    }

    @Override
    public Item updateItem(Item item, long l) {
        return null;
    }

    @Override
    public void deleteItemById(long id) {

    }

   
    
}