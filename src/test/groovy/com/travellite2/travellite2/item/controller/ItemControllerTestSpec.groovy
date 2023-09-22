package com.travellite2.travellite2.item.controller

import spock.lang.Specification
import com.travellite2.travellite2.item.entity.Item
import com.travellite2.travellite2.item.service.ItemService

class ItemControllerSpec extends Specification {

    def itemServiceMock = Mock(ItemService)
    def itemController = new ItemController(itemServiceMock)

    def "getItemsBySearch should return a list of items"() {

        given:

        def itemName = "Test Item"
        def items = [
                new Item(itemId: 1, itemName: itemName)
        ]


        when:
        def result = itemController.getItemsBySearch(itemName)

        then:
        1 * itemServiceMock.findByItemNameContainingIgnoreCase(itemName) >> items
        0 * itemServiceMock.getAllItems(_)
        result.size() == 1
        result[0].itemId == 1
        result[0].itemName == "Test Item"
    }

    def "getItemsByName should return a list of items"() {
        given:

        def itemName = "Test Item"
        def items = [new Item(itemId: 1, itemName: itemName)]


        when:
        def result = itemController.getItemsByName(itemName)

        then:
        1 * itemServiceMock.findByItemNameContainingIgnoreCase(itemName) >> items

        result.size() == 1
        result[0].itemId == 1
        result[0].itemName == "Test Item"
    }

    def "getItemsByType should return a list of items"() {
        given:

        def itemType = "Test Type"
        def items = [new Item(itemId: 1, itemTypeId: itemType)]


        when:
        def result = itemController.getItemsByType(itemType)

        then:
        1 * itemServiceMock.findByItemType(itemType) >> items

        result.size() == 1
        result[0].itemId == 1
        result[0].itemTypeId == "Test Type"
    }

    def "getItemsTypeByPathVariable should return a list of items"() {
        given:

        def itemType = "Test Type"
        def items = [new Item(itemId: 1, itemTypeId: itemType)]


        when:
        def result = itemController.getItemsTypeByPathVariable(itemType)

        then:
        1 * itemServiceMock.findByItemType(itemType) >> items

        result.size() == 1
        result[0].itemId == 1
        result[0].itemTypeId == "Test Type"
    }

    def "getAllItemTypes should return a list of item types"() {
        given:

        def itemTypes = ["Type1", "Type2", "Type3"]


        when:
        def result = itemController.getAllItemTypes()

        then:
        1 * itemServiceMock.getAllItemTypes() >> itemTypes

        result.size() == 3
    }
}

