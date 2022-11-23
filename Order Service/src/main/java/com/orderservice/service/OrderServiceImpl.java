package com.orderservice.service;

import com.orderservice.entity.Order;
import com.orderservice.model.OrderRequest;
import com.orderservice.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.set
        return null;
    }
}
