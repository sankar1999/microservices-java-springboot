package com.orderservice.service;

import com.orderservice.entity.Order;
import com.orderservice.model.OrderRequest;
import com.orderservice.model.OrderResponse;

import java.util.List;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(Long orderId);

    List<Order> getAllOrders();
}
