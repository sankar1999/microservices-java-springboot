package com.orderservice.service;

import com.orderservice.entity.Order;
import com.orderservice.exception.CustomException;
import com.orderservice.external.client.PaymentService;
import com.orderservice.external.client.ProductService;
import com.orderservice.external.client.request.PaymentRequest;
import com.orderservice.external.client.response.PaymentResponse;
import com.orderservice.external.client.response.ProductResponse;
import com.orderservice.model.OrderRequest;
import com.orderservice.model.OrderResponse;
import com.orderservice.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Long placeOrder(OrderRequest orderRequest) {

        // Calling Product Service...
        productService.reduceQuantity(orderRequest.getProductId(),
                orderRequest.getQuantity());

        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        order = orderRepository.save(order);

        // Calling Payment Service
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();

        String orderStatus = null;
        try {
            paymentService.doPayment(paymentRequest);
            orderStatus = "PLACED";
        } catch (Exception e) {
            orderStatus = "PAYMENT_FAILED";
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new CustomException("Order not found for the order Id: " + orderId +
                                " NOT_FOUND", "UNAVAILABLE", 404));

        ProductResponse productResponse
                = restTemplate.getForObject(
                        "http://PRODUCT-SERVICE/product/getProduct/" + order.getProductId(),
                ProductResponse.class
        );

        OrderResponse.ProductDetails productDetails
                = OrderResponse.ProductDetails
                .builder()
                .productName(productResponse.getProductName())
                .productId(productResponse.getProductId())
                .quantity(productResponse.getQuantity())
                .price(productResponse.getPrice())
                .build();

        //Getting payment details
        PaymentResponse paymentResponse
                = restTemplate.getForObject("http://PAYMENT-SERVICE/payment/getpaymentDetailsByOrderId/"
                                                + order.getId(),
                PaymentResponse.class);

        OrderResponse.PaymentDetails paymentDetails
                = OrderResponse.PaymentDetails
                .builder()
                .paymentId(paymentResponse.getPaymentId())
                .status(paymentResponse.getStatus())
                .paymentDate(paymentResponse.getPaymentDate())
                .paymentMode(paymentResponse.getPaymentMode())
                .build();

        OrderResponse orderResponse =
                OrderResponse.builder()
                        .orderId(order.getId())
                        .orderStatus(order.getOrderStatus())
                        .amount(order.getAmount())
                        .orderDate(order.getOrderDate())
                        .productDetails(productDetails)
                        .paymentDetails(paymentDetails)
                        .build();

        return orderResponse;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders;
    }


}
