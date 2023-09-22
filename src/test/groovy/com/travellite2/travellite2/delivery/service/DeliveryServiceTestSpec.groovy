package com.travellite2.travellite2.delivery.service

import spock.lang.Specification
import com.travellite2.travellite2.delivery.service.DeliveryService
import com.travellite2.travellite2.delivery.entity.Delivery
import com.travellite2.travellite2.delivery.model.DeliveryJson
import com.travellite2.travellite2.delivery.converter.DeliveryToDeliveryJsonConverter
import com.travellite2.travellite2.delivery.repository.DeliveryRepository
import com.travellite2.travellite2.register.repository.UserRepository

class DeliveryServiceSpec extends Specification {

    def "createAddress should save the delivery entity and return it"() {
        given:
        def deliveryRepositoryMock = Mock(DeliveryRepository)
        def userRepositoryMock = Mock(UserRepository)
        def deliveryToDeliveryJsonConverterMock = Mock(DeliveryToDeliveryJsonConverter)

        def deliveryService = new DeliveryService(deliveryRepositoryMock, deliveryToDeliveryJsonConverterMock, userRepositoryMock)

        def deliveryJson = new DeliveryJson(
                userName: "testUser",
                address: "123 Main St",
                country: "US",
                postcode: "12345",
                bundleOrderId: 1
        )

        def userId = 1
        userRepositoryMock.findByUserName(deliveryJson.userName) >> [id: userId]

        def deliveryEntity = new Delivery(
                deliveryId: 1,
                address: deliveryJson.address,
                country: deliveryJson.country,
                postcode: deliveryJson.postcode,
                bundleOrderId: deliveryJson.bundleOrderId,
                userId: userId
        )

        deliveryToDeliveryJsonConverterMock.convert(deliveryJson) >> deliveryEntity
        deliveryRepositoryMock.save(deliveryEntity) >> deliveryEntity

        when:
        def createdDelivery = deliveryService.createAddress(deliveryJson)

        then:
        1 * userRepositoryMock.findByUserName(deliveryJson.userName) >> [id: userId]
        1 * deliveryToDeliveryJsonConverterMock.convert(deliveryJson) >> deliveryEntity
        1 * deliveryRepositoryMock.save(deliveryEntity) >> deliveryEntity

        createdDelivery == deliveryEntity
    }
}


