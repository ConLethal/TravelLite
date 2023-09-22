package com.travellite2.travellite2.delivery.controller;


import com.travellite2.travellite2.delivery.entity.Delivery;
import com.travellite2.travellite2.delivery.model.DeliveryJson;
import com.travellite2.travellite2.delivery.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@CrossOrigin
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/delivery")
    public ResponseEntity<Delivery> createAddress(@RequestBody DeliveryJson deliveryJson) {

        Delivery delivery = deliveryService.createAddress(deliveryJson);

        return new ResponseEntity<Delivery>(delivery, HttpStatus.CREATED);
    }
}
