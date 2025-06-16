package com.task.test.order.dto;

import com.task.test.product.entity.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCreateRequest {
    private final Product product;
    private final int quantity;

    public static OrderCreateRequest of(final Product product, final int quantity) {
        return new OrderCreateRequest(product, quantity);
    }
}
