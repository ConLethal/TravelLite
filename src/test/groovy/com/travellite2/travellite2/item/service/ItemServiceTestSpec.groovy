package com.travellite2.travellite2.item.service

import spock.lang.Specification
import com.travellite2.travellite2.item.converters.ItemToItemJsonConverter
import com.travellite2.travellite2.item.converters.ItemTypeToItemTypeJsonConverter
import com.travellite2.travellite2.item.entity.Item
import com.travellite2.travellite2.item.entity.ItemType
import com.travellite2.travellite2.item.repository.ItemRepository
import com.travellite2.travellite2.item.repository.ItemTypeRepository

class ItemServiceSpec extends Specification {

    def "getAllItems should return a list of items"() {
        given:
        def itemRepository = Mock(ItemRepository)
        def itemToItemJsonConverter = Mock(ItemToItemJsonConverter)
        def itemTypeRepository = Mock(ItemTypeRepository)
        def itemTypeToItemTypeJsonConverter = Mock(ItemTypeToItemTypeJsonConverter)
        def itemService = new ItemService(itemRepository, itemToItemJsonConverter, itemTypeRepository, itemTypeToItemTypeJsonConverter)

        when:
        itemRepository.findAll() >> [new Item(itemName: "Item 1"), new Item(itemName: "Item 2")]

        then:
        def items = itemService.getAllItems()
        items.size() == 2
        items[0].itemName == "Item 1"
        items[1].itemName == "Item 2"
    }

    def "findByItemType should return a list of items based on the item type"() {
        given:
        def itemRepository = Mock(ItemRepository)
        def itemToItemJsonConverter = Mock(ItemToItemJsonConverter)
        def itemTypeRepository = Mock(ItemTypeRepository)
        def itemTypeToItemTypeJsonConverter = Mock(ItemTypeToItemTypeJsonConverter)
        def itemService = new ItemService(itemRepository, itemToItemJsonConverter, itemTypeRepository, itemTypeToItemTypeJsonConverter)
        def itemType = "TestType"

        when:
        itemRepository.findByItemTypeId(itemType) >> [new Item(itemName: "Item 1")]

        then:
        def items = itemService.findByItemType(itemType)
        items.size() == 1
        items[0].itemName == "Item 1"
    }

    def "findByItemNameContainingIgnoreCase should return a list of items based on the item name"() {
        given:
        def itemRepository = Mock(ItemRepository)
        def itemToItemJsonConverter = Mock(ItemToItemJsonConverter)
        def itemTypeRepository = Mock(ItemTypeRepository)
        def itemTypeToItemTypeJsonConverter = Mock(ItemTypeToItemTypeJsonConverter)
        def itemService = new ItemService(itemRepository, itemToItemJsonConverter, itemTypeRepository, itemTypeToItemTypeJsonConverter)
        def itemName = "item"

        when:
        itemRepository.findByItemNameContainingIgnoreCase(itemName) >> [new Item(itemName: "Item 1")]

        then:
        def items = itemService.findByItemNameContainingIgnoreCase(itemName)
        items.size() == 1
        items[0].itemName == "Item 1"
    }

    def "getAllItemTypes should return a list of item types"() {
        given:
        def itemRepository = Mock(ItemRepository)
        def itemToItemJsonConverter = Mock(ItemToItemJsonConverter)
        def itemTypeRepository = Mock(ItemTypeRepository)
        def itemTypeToItemTypeJsonConverter = Mock(ItemTypeToItemTypeJsonConverter)
        def itemService = new ItemService(itemRepository, itemToItemJsonConverter, itemTypeRepository, itemTypeToItemTypeJsonConverter)

        when:
        itemTypeRepository.findAll() >> [new ItemType(itemTypeId: 1)]

        then:
        def itemTypes = itemService.getAllItemTypes()
        itemTypes.size() == 1
       // itemTypes[0].itemTypeName == 1
    }
}

