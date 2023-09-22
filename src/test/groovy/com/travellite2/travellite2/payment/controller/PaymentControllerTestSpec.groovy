package com.travellite2.travellite2.payment.controller

import spock.lang.Specification
import com.travellite2.travellite2.payment.model.PaymentJson
import com.travellite2.travellite2.payment.service.PaymentService
import org.springframework.http.HttpStatus

class PaymentControllerSpec extends Specification {


    def "createPayment should return CREATED response when payment is created successfully"() {
        given:
        def paymentService = Mock(PaymentService)
        def paymentController = new PaymentController(paymentService)
        def paymentJson = new PaymentJson() // Fill with required fields

        when:
                paymentService.createPayment(paymentJson)

        then:
        1 * paymentService.createPayment(paymentJson) >> {} // Mock successful payment creation

        and:
        def response = paymentController.createPayment(paymentJson)

        expect:
        response.status == HttpStatus.CREATED
    }

}
