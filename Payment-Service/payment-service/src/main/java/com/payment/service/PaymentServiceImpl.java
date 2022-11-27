package com.payment.service;

import com.payment.model.PaymentRequest;
import com.payment.repository.TransactionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private TransactionDetailsRepository repository;

    @Override
    public Object dopayment(PaymentRequest paymentRequest) {
        return null;
    }
}
