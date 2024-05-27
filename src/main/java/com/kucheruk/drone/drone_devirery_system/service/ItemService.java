package com.kucheruk.drone.drone_devirery_system.service;

import com.kucheruk.drone.drone_devirery_system.model.Item;
import com.kucheruk.drone.drone_devirery_system.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    public Item register(Item item)
    {
        return itemRepository.save(item);
    }
    public List<Item> findAll()
    {
        return itemRepository.findAll();
    }
    public Item get(String code) {
        Optional<Item> optionalItem = itemRepository.findByCode(code);
        return optionalItem.orElse(null);
    }
}
