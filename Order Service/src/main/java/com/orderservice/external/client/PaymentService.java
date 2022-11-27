package com.orderservice.external.client;

import com.orderservice.exception.CustomException;
import com.orderservice.external.client.request.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentService {

    @PostMapping("/doPayment")
    public ResponseEntity<Object> doPayment(@RequestBody PaymentRequest paymentRequest);

    default void fallback(Exception e) {
        throw new CustomException("Payment server is not available",
                "UNAVAILABLE",
                500);
    }

}
