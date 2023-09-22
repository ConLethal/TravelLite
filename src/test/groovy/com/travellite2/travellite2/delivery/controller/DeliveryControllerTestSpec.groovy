package com.travellite2.travellite2.delivery.controller

import spock.lang.Specification
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import com.travellite2.travellite2.delivery.controller.DeliveryController
import com.travellite2.travellite2.delivery.entity.Delivery
import com.travellite2.travellite2.delivery.model.DeliveryJson
import com.travellite2.travellite2.delivery.service.DeliveryService

class DeliveryControllerSpec extends Specification {

    def "createAddress should return HTTP status CREATED and the created Delivery entity"() {
        given:
        def deliveryServiceMock = Mock(DeliveryService)
        def deliveryController = new DeliveryController(deliveryServiceMock)

        def deliveryJson = new DeliveryJson(
                address: "123 Main St",
                country: "US",
                postcode: "12345",
                bundleOrderId: 1
        )
        def createdDelivery = new Delivery(
                deliveryId: 1,
                address: deliveryJson.address,
                country: deliveryJson.country,
                postcode: deliveryJson.postcode,
                bundleOrderId: deliveryJson.bundleOrderId
        )

        deliveryServiceMock.createAddress(deliveryJson) >> createdDelivery

        when:
        def responseEntity = deliveryController.createAddress(deliveryJson)

        then:
        1 * deliveryServiceMock.createAddress(deliveryJson) >> createdDelivery
        responseEntity instanceof ResponseEntity
        responseEntity.statusCode == HttpStatus.CREATED
        responseEntity.body == createdDelivery
    }
}

