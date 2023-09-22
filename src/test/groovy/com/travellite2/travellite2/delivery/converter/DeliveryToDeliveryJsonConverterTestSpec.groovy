package com.travellite2.travellite2.delivery.converter

import spock.lang.Specification
import com.travellite2.travellite2.delivery.converter.DeliveryToDeliveryJsonConverter
import com.travellite2.travellite2.delivery.entity.Delivery
import com.travellite2.travellite2.delivery.model.DeliveryJson

class DeliveryToDeliveryJsonConverterSpec extends Specification {

    def "convert should map DeliveryJson to Delivery entity correctly"() {
        given:
        def deliveryToDeliveryJsonConverter = new DeliveryToDeliveryJsonConverter()

        def deliveryJson = new DeliveryJson(
                address: "123 Main St",
                country: "US",
                postcode: "12345",
                bundleOrderId: 1
        )

        when:
        def deliveryEntity = deliveryToDeliveryJsonConverter.convert(deliveryJson)

        then:
        deliveryEntity instanceof Delivery
        deliveryEntity.address == deliveryJson.address
        deliveryEntity.country == deliveryJson.country
        deliveryEntity.postcode == deliveryJson.postcode
        deliveryEntity.bundleOrderId == deliveryJson.bundleOrderId
    }
}
