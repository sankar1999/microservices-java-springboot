package com.product.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProductRequest {

    private String name;
    private long price;
    private long quantity;


}
