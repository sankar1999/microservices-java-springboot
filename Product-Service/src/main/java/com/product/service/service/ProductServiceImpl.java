package com.product.service.service;

import com.product.service.entity.Product;
import com.product.service.model.ProductRequest;
import com.product.service.model.ProductRequestForUpdate;
import com.product.service.model.ProductResponse;
import com.product.service.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product...");
        Product product = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product Created...");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with given id not found..."));
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);

        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductResponse> productResponses = products.stream()
                .map(p -> {
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setProductId(p.getProductId());
                    productResponse.setProductName(p.getProductName());
                    productResponse.setPrice(p.getPrice());
                    productResponse.setQuantity(p.getQuantity());
                    return productResponse;
                })
                .collect(Collectors.toList());
        productResponses.forEach(System.out::println);
        return productResponses;
    }

    @Override
    public void saveListOfProducts(List<ProductRequest> productRequests) {
        List<Product> products = (List<Product>) productRequests.stream()
                .map(p -> new Product(p.getName(), p.getPrice(), p.getQuantity()))
                .collect(Collectors.toList());
        products.forEach(System.out::println);
        productRepository.saveAll(products);
    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Override
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void updateSingleProduct(ProductRequestForUpdate productRequestForUpdate) {
        Long productId = productRequestForUpdate.getProductId();
        Optional<Product> getProduct =
                productRepository.findById(productId);

        Product product = new Product();
        product.setProductId(productRequestForUpdate.getProductId());
        product.setProductName(productRequestForUpdate.getProductName());
        product.setPrice(productRequestForUpdate.getPrice());
        product.setQuantity(productRequestForUpdate.getQuantity());

        System.out.println(product);

        productRepository.save(product);
    }

    @Override
    public void updateListOfProducts(List<ProductRequestForUpdate> productsRequest) {
        productsRequest.forEach(System.out::println);
        List<Product> products = productsRequest.stream()
                .map(p -> {
                    Product product = new Product();
                    product.setProductId(p.getProductId());
                    product.setProductName(p.getProductName());
                    product.setPrice(p.getPrice());
                    product.setQuantity(p.getQuantity());
                    return product;
                }).collect(Collectors.toList());
        productRepository.saveAll(products);
    }


}
