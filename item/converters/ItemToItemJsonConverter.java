package com.travellite2.travellite2.item.converters;

import com.travellite2.travellite2.item.entity.Item;
import com.travellite2.travellite2.item.model.ItemJson;
import org.springframework.stereotype.Component;

@Component
    public class ItemToItemJsonConverter {

        public Item convert(ItemJson item) {
             //converts entity to Json
            Item itemEntity = new Item();

            itemEntity.setItemName(item.getItemName());
            itemEntity.setItemDescription(item.getItemDescription());
            itemEntity.setItemTypeId(item.getItemType());
            itemEntity.setItemPrice(item.getPrice());
            itemEntity.setItemImageUrl(item.getItemImageUrl());

            return itemEntity;
        }
    }

