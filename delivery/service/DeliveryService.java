package com.travellite2.travellite2.delivery.service;

import com.travellite2.travellite2.delivery.converter.DeliveryToDeliveryJsonConverter;
import com.travellite2.travellite2.delivery.entity.Delivery;
import com.travellite2.travellite2.delivery.model.DeliveryJson;
import com.travellite2.travellite2.delivery.repository.DeliveryRepository;
//import com.travellite2.travellite2.userOrder.repository.UserOrderRepository;
import com.travellite2.travellite2.register.repository.UserRepository;
import org.springframework.stereotype.Component;


@Component
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryToDeliveryJsonConverter deliveryToDeliveryJsonConverter;

    private final UserRepository userRepository;

    public DeliveryService(DeliveryRepository deliveryRepository, DeliveryToDeliveryJsonConverter deliveryToDeliveryJsonConverter, UserRepository userRepository) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryToDeliveryJsonConverter = deliveryToDeliveryJsonConverter;
        this.userRepository = userRepository;
    }

    public Delivery createAddress(DeliveryJson deliveryJson) {

        //finds and converts the username to userId
        int userId = userRepository.findByUserName(deliveryJson.getUserName()).getId();


        Delivery deliveryEntity = deliveryToDeliveryJsonConverter.convert(deliveryJson);
        deliveryEntity.setUserId(userId);

        return deliveryRepository.save(deliveryEntity);


    }
}
