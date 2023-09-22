package com.travellite2.travellite2.payment.service

import com.travellite2.travellite2.register.entity.User
import spock.lang.Specification
import com.travellite2.travellite2.payment.converter.PaymentToPaymentJsonConverter
import com.travellite2.travellite2.payment.entity.Payment
import com.travellite2.travellite2.payment.model.PaymentJson
import com.travellite2.travellite2.payment.repository.PaymentRepository
import com.travellite2.travellite2.register.repository.UserRepository

class PaymentServiceSpec extends Specification {

    def paymentRepository = Mock(PaymentRepository)
    def paymentToPaymentJsonConverter = Mock(PaymentToPaymentJsonConverter)
    def userRepository = Mock(UserRepository)

    def paymentService = new PaymentService(paymentRepository, paymentToPaymentJsonConverter, userRepository)


    def "createPayment should save a Payment entity with the correct user ID"() {

        given:

        def paymentJson = new PaymentJson()
        paymentJson.setLongNumber("1234567812345678")
        paymentJson.setNameOnCard("John Doe")
        paymentJson.setExpiry("12/25")
        paymentJson.setSecurityCode("123")
        paymentJson.setPaid(100.0)
        paymentJson.setBundleOrderId(12)
        paymentJson.setUserName("username")

        def user = new User()
        user.userName = "username"
        user.id = 1

        def paymentEntity = new Payment()
        paymentEntity.longNumber = paymentJson.longNumber
        paymentEntity.expiry = "12/25"
        paymentEntity.nameOnCard = "John Doe"
        paymentEntity.securityCode = "123"
        paymentEntity.paid = 100.0
        paymentEntity.bundleOrderId = 12

        when:
        paymentService.createPayment(paymentJson)

        then:
        1 * userRepository.findByUserName(paymentJson.getUserName()) >> {user}
        1 * paymentToPaymentJsonConverter.convert(_) >> paymentEntity
        1 * paymentRepository.save(paymentEntity)
    }
}

