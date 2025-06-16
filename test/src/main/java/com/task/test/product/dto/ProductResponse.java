package com.task.test.product.dto;

import com.task.test.product.entity.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductResponse {
    private final long id;
    private final String name;
    private final int price;

    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }
}
