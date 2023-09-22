package com.travellite2.travellite2.payment.controller;


import com.travellite2.travellite2.payment.model.PaymentJson;
import com.travellite2.travellite2.payment.service.PaymentService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pay")
@CrossOrigin
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){this.paymentService = paymentService;}

    @PostMapping("/carddetails")
    public ResponseEntity<String> createPayment(@RequestBody PaymentJson payment) {
        try {
            paymentService.createPayment(payment);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating payment details");
        }

    }
}
