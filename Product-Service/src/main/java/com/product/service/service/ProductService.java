package com.product.service.service;

import com.product.service.model.ProductRequest;
import com.product.service.model.ProductRequestForUpdate;
import com.product.service.model.ProductResponse;

import java.util.List;

public interface ProductService {

    long addProduct(ProductRequest productRequest);

    ProductResponse getProduct(long id);

    List<ProductResponse> getAllProducts();

    void saveListOfProducts(List<ProductRequest> productRequests);

    void deleteAllProducts();

    void deleteProductById(long id);

    void updateSingleProduct(ProductRequestForUpdate productRequestForUpdate);

    void updateListOfProducts(List<ProductRequestForUpdate> productsRequest);

    void reduceQuantity(Long productId, long quantity);

    void increaseQuantity(Long productId, long quantity);
}
