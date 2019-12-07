package coffee.inventory.service;

import java.util.Collection;

import coffee.inventory.entity.Item;

public interface ItemService {
    Collection<Item> findAllItemsById(Integer... itemIds);
}