package com.travellite2.travellite2.delivery.converter;


import com.travellite2.travellite2.delivery.entity.Delivery;
import com.travellite2.travellite2.delivery.model.DeliveryJson;
import org.springframework.stereotype.Component;

@Component
public class DeliveryToDeliveryJsonConverter {

    public Delivery convert(DeliveryJson deliveryJson) {
        //converts entity to Json
        Delivery deliveryEntity = new Delivery();

        deliveryEntity.setAddress(deliveryJson.getAddress());
        deliveryEntity.setCountry(deliveryJson.getCountry());
        deliveryEntity.setPostcode(deliveryJson.getPostcode());
        deliveryEntity.setBundleOrderId(deliveryJson.getBundleOrderId());

        return deliveryEntity;
    }

}
