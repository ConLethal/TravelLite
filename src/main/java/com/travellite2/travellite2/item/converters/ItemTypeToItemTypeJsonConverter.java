package com.travellite2.travellite2.item.converters;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.travellite2.travellite2.item.entity.ItemType;
import com.travellite2.travellite2.item.model.ItemTypeJson;
import org.springframework.stereotype.Component;

@Component
public class ItemTypeToItemTypeJsonConverter implements Converter<ItemType, ItemTypeJson> {

    @Override
    public ItemTypeJson convert(ItemType itemType) {
        //converts item type Json to item type entity
        ItemTypeJson itemTypeJson = new ItemTypeJson();
        itemTypeJson.setItemTypeName(itemType.getItemTypeName());
        itemTypeJson.setItemTypeId(itemType.getItemTypeId());

        return itemTypeJson;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
