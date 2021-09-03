package com.root.Generic.AplicationLayer.JPA.Servicies;

import java.util.List;

import com.root.Generic.AplicationLayer.JPA.Models.Item;




public interface ItemService {

    public void createItem(Item item);
    public List<Item> getItems();
    public Item findItemsById(long id);
    public Item updateItem(Item item, long l);
    public void deleteItemById(long id);

}