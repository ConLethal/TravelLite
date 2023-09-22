package com.travellite2.travellite2.payment.repository;

import com.travellite2.travellite2.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
