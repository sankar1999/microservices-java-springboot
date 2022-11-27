package com.payment.service;

import com.payment.model.PaymentRequest;
import com.payment.model.PaymentResponse;

public interface PaymentService {

    Object dopayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(Long orderId);
}
