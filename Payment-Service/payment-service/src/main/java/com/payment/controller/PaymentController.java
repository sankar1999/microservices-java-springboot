package com.payment.controller;

import com.payment.model.PaymentRequest;
import com.payment.model.PaymentResponse;
import com.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public ResponseEntity<Object> doPayment(@RequestBody PaymentRequest paymentRequest) {
        return new ResponseEntity<>(
                paymentService.dopayment(paymentRequest), HttpStatus.OK
        );
    }

    @GetMapping("/getpaymentDetailsByOrderId/{orderId}")
    public ResponseEntity<PaymentResponse>
    getPaymentDetailsByOrderId(@PathVariable Long orderId) {
        return new ResponseEntity<>(
                paymentService.getPaymentDetailsByOrderId(orderId),
                HttpStatus.OK
        );

    }
}
