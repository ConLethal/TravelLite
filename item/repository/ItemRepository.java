package com.travellite2.travellite2.item.repository;

import com.travellite2.travellite2.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByItemTypeId(String itemType);
    List<Item> findByItemNameContainingIgnoreCase(String itemName);

    Item findByItemId(int itemId);



}
