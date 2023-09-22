package com.travellite2.travellite2.userOrder.converter

import com.travellite2.travellite2.userOrder.model.ItemRequest
import spock.lang.Specification

class UserOrderToItemRequestJsonConverterTest extends Specification {

    def "convert should convert ItemRequest to UserOrder"() {
        given:
        def converter = new UserOrderToItemRequestJsonConverter()
        def itemRequest = new ItemRequest(itemId: 1, itemQuantity: 2, itemPrice: 10.0)

        when:
        def userOrder = converter.convert(itemRequest)

        then:
        userOrder.itemId == itemRequest.itemId
        userOrder.itemQuantity == itemRequest.itemQuantity
        userOrder.itemPrice == itemRequest.itemPrice
    }
}
