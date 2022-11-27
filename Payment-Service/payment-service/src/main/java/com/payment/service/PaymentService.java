package com.payment.service;

import com.payment.model.PaymentRequest;

public interface PaymentService {

    Object dopayment(PaymentRequest paymentRequest);
}
