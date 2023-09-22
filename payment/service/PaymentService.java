package com.travellite2.travellite2.payment.service;


import com.travellite2.travellite2.payment.converter.PaymentToPaymentJsonConverter;
import com.travellite2.travellite2.payment.entity.Payment;
import com.travellite2.travellite2.payment.model.PaymentJson;
import com.travellite2.travellite2.payment.repository.PaymentRepository;
import com.travellite2.travellite2.register.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentToPaymentJsonConverter paymentToPaymentJsonConverter;

    private final UserRepository userRepository;

    public PaymentService(PaymentRepository paymentRepository,
                          PaymentToPaymentJsonConverter paymentToPaymentJsonConverter,
                          UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentToPaymentJsonConverter = paymentToPaymentJsonConverter;
        this.userRepository = userRepository;
    }

public void createPayment(PaymentJson payment) {

        int userId = userRepository.findByUserName(payment.getUserName()).getId();

        Payment paymentEntity = paymentToPaymentJsonConverter.convert(payment);
        paymentEntity.setUserId(userId);

    paymentRepository.save(paymentEntity);

}


}
