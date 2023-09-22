package com.travellite2.travellite2.item.controller;

import com.travellite2.travellite2.item.entity.Item;
import com.travellite2.travellite2.item.model.ItemTypeJson;
import com.travellite2.travellite2.item.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {


    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public List<Item> getItemsBySearch(@RequestParam(required = false) String itemName) {
        List<Item> items;

        if (itemName != null) {
            items = itemService.findByItemNameContainingIgnoreCase(itemName);
        } else {
            items = itemService.getAllItems();
        }

        return items;
    }

    @GetMapping("/items/{itemname}")
    public List<Item> getItemsByName(@PathVariable String itemname) {
        List<Item> item = new ArrayList<>();

        if(itemname != null) {
            item = itemService.findByItemNameContainingIgnoreCase(itemname);
        }

        return item;

    }

    @GetMapping("/searchbyitemtype/{itemType}")
    public List<Item> getItemsByType(@PathVariable String itemType) {

        List<Item> items = new ArrayList<>();

        if (itemType != null) {
            items = itemService.findByItemType(itemType);
        }

        return items;

    }

    @GetMapping("/items/type/{itemtype}")
    public List<Item> getItemsTypeByPathVariable(@RequestParam String itemType) {
        return itemService.findByItemType(itemType);
    }

    @GetMapping("/itemtypes")
    public List<ItemTypeJson> getAllItemTypes() {

        List<ItemTypeJson> itemTypeJsonList = itemService.getAllItemTypes();

        return itemTypeJsonList;
    }

}
