package com.travellite2.travellite2.delivery.repository;

import com.travellite2.travellite2.delivery.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

 Delivery findByDeliveryId(int deliveryId);

}
