package com.product.service.controller;

import com.product.service.model.ProductRequest;
import com.product.service.model.ProductRequestForUpdate;
import com.product.service.model.ProductResponse;
import com.product.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {
        long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }

    @GetMapping("/getProduct/{id}")
    public ProductResponse getProduct(@PathVariable("id") long id) {
        ProductResponse productResponse = productService.getProduct(id);
        return productResponse;
    }

    @GetMapping("/getAllProducts")
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> productResponses =
                productService.getAllProducts();
        return productResponses;
    }

    @PostMapping("/saveListOfProducts")
    public String saveListOfProducts(@RequestBody List<ProductRequest> productRequests) {
        productService.saveListOfProducts(productRequests);
        return "Success";
    }

    @DeleteMapping("/deleteAllProducts")
    public String deleteAllProducts() {
        productService.deleteAllProducts();
        return "Success";
    }

    @DeleteMapping("/deleteProductById/{id}")
    public String deleteProductById(@PathVariable("id") long id) {
        productService.deleteProductById(id);
        return "Success";
    }

    @PostMapping("/updateSingleProduct")
    public String updateSingleProduct(@RequestBody ProductRequestForUpdate productRequestForUpdate) {
        productService.updateSingleProduct(productRequestForUpdate);
        return "Success";
    }

    @PostMapping("/updateListOfProducts")
    public void updateListOfProducts(@RequestBody List<ProductRequestForUpdate> productsRequest) {
        productService.updateListOfProducts(productsRequest);
    }


}
