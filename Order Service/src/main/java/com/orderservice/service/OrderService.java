package com.orderservice.service;

import com.orderservice.model.OrderRequest;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);
}
