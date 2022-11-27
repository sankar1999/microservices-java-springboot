package com.orderservice.service;

import com.orderservice.model.OrderRequest;
import com.orderservice.model.OrderResponse;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(Long orderId);
}
