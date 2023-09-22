package com.travellite2.travellite2.payment.converter

import spock.lang.Specification
import com.travellite2.travellite2.payment.model.PaymentJson

class PaymentToPaymentJsonConverterSpec extends Specification {

    def "convert should correctly convert a PaymentJson object to a Payment entity"() {
        given:
        def paymentConverter = new PaymentToPaymentJsonConverter()
        def paymentJson = new PaymentJson(
                longNumber: "1234567812345678",
                nameOnCard: "John Doe",
                expiry: "12/25",
                securityCode: "123",
                paid: 100.0,
                bundleOrderId: 123
        )

        when:
        def paymentEntity = paymentConverter.convert(paymentJson)

        then:
        paymentEntity.longNumber == paymentJson.longNumber
        paymentEntity.nameOnCard == paymentJson.nameOnCard
        paymentEntity.expiry == paymentJson.expiry
        paymentEntity.securityCode == paymentJson.securityCode
        paymentEntity.paid == paymentJson.paid
        paymentEntity.bundleOrderId == paymentJson.bundleOrderId
    }
}

