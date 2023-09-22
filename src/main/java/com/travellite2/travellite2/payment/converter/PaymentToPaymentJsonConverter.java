package com.travellite2.travellite2.payment.converter;


import com.travellite2.travellite2.payment.entity.Payment;
import com.travellite2.travellite2.payment.model.PaymentJson;
import org.springframework.stereotype.Component;

@Component
public class PaymentToPaymentJsonConverter {

    public Payment convert(PaymentJson payment) {
        Payment paymentEntity = new Payment();
        //converts entity to Json
        paymentEntity.setLongNumber(payment.getLongNumber());
        paymentEntity.setNameOnCard(payment.getNameOnCard());
        paymentEntity.setExpiry(payment.getExpiry());
        paymentEntity.setSecurityCode(payment.getSecurityCode());
        paymentEntity.setPaid(payment.getPaid());
        paymentEntity.setBundleOrderId(payment.getBundleOrderId());

        return paymentEntity;
    }
}
