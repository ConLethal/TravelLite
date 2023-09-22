package com.travellite2.travellite2.item.converter

import spock.lang.Specification
import com.travellite2.travellite2.item.converters.ItemTypeToItemTypeJsonConverter
import com.travellite2.travellite2.item.entity.ItemType
import com.travellite2.travellite2.item.model.ItemTypeJson

class ItemTypeToItemTypeJsonConverterSpec extends Specification {

    def "convert should return an ItemTypeJson object"() {
        given:
        def itemTypeToItemTypeJsonConverter = new ItemTypeToItemTypeJsonConverter()
        def itemType = new ItemType(itemTypeName: "Test Type", itemTypeId: 1)

        when:
        def result = itemTypeToItemTypeJsonConverter.convert(itemType)

        then:
        result instanceof ItemTypeJson
        result.itemTypeName == itemType.itemTypeName
        result.itemTypeId == itemType.itemTypeId
    }

    def "getInputType should return null"() {
        given:
        def itemTypeToItemTypeJsonConverter = new ItemTypeToItemTypeJsonConverter()

        when:
        def inputType = itemTypeToItemTypeJsonConverter.getInputType(null)

        then:
        inputType == null
    }

    def "getOutputType should return null"() {
        given:
        def itemTypeToItemTypeJsonConverter = new ItemTypeToItemTypeJsonConverter()

        when:
        def outputType = itemTypeToItemTypeJsonConverter.getOutputType(null)

        then:
        outputType == null
    }
}
