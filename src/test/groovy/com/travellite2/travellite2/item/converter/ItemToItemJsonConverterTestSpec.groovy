package com.travellite2.travellite2.item.converter

import spock.lang.Specification
import com.travellite2.travellite2.item.converters.ItemToItemJsonConverter
import com.travellite2.travellite2.item.entity.Item
import com.travellite2.travellite2.item.model.ItemJson

class ItemToItemJsonConverterSpec extends Specification {

    def "convert should return an Item entity"() {
        given:
        def itemToItemJsonConverter = new ItemToItemJsonConverter()
        def itemJson = new ItemJson(
                itemName: "Test Item",
                itemDescription: "Test Description",
                itemType: "Test Type",
                price: 100.0,
                itemImageUrl: "http://example.com/test.jpg"
        )

        when:
        def result = itemToItemJsonConverter.convert(itemJson)

        then:
        result instanceof Item
        result.itemName == itemJson.itemName
        result.itemDescription == itemJson.itemDescription
        result.itemTypeId == itemJson.itemType
        result.itemPrice == itemJson.price
        result.itemImageUrl == itemJson.itemImageUrl
    }
}
