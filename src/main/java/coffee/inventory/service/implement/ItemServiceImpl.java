package coffee.inventory.service.implement;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.stereotype.Service;

import coffee.inventory.entity.Item;
import coffee.inventory.repository.ItemRepository;
import coffee.inventory.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Collection<Item> findAllItemsById(Integer... itemIds) {

        return itemRepository.findAllById(Arrays.asList(itemIds));
    }

}