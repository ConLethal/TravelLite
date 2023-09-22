package com.travellite2.travellite2.item.service;

import com.travellite2.travellite2.item.converters.ItemToItemJsonConverter;
import com.travellite2.travellite2.item.converters.ItemTypeToItemTypeJsonConverter;
import com.travellite2.travellite2.item.entity.Item;
import com.travellite2.travellite2.item.entity.ItemType;
import com.travellite2.travellite2.item.model.ItemTypeJson;
import com.travellite2.travellite2.item.repository.ItemRepository;
import com.travellite2.travellite2.item.repository.ItemTypeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemService {

    private final ItemRepository itemRepository;
    public final ItemToItemJsonConverter itemToItemJsonConverter;

    private final  ItemTypeToItemTypeJsonConverter itemTypeToItemTypeJsonConverter;
    private final ItemTypeRepository itemTypeRepository;

    public ItemService(ItemRepository itemRepository, ItemToItemJsonConverter itemToItemJsonConverter, ItemTypeRepository itemTypeRepository, ItemTypeToItemTypeJsonConverter itemTypeToItemTypeJsonConverter) {
        this.itemRepository = itemRepository;
        this.itemToItemJsonConverter = itemToItemJsonConverter;
        this.itemTypeRepository = itemTypeRepository;
        this.itemTypeToItemTypeJsonConverter = itemTypeToItemTypeJsonConverter;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> findByItemType(String itemType) {

        return itemRepository.findByItemTypeId(itemType);
    }

    public List<Item> findByItemNameContainingIgnoreCase(String itemName) {

            return itemRepository.findByItemNameContainingIgnoreCase(itemName);

    }

    public List<ItemTypeJson> getAllItemTypes() {
        List<ItemType> itemTypeList = itemTypeRepository.findAll();

        List<ItemTypeJson> itemTypeJsonList;
        itemTypeJsonList = itemTypeList.stream()
                .map(itemTypeToItemTypeJsonConverter::convert)
                .collect(Collectors.toList());

        return itemTypeJsonList;
    }
}
