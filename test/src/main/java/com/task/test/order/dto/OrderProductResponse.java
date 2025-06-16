package com.task.test.order.dto;

import com.task.test.order.entity.OrderProduct;
import com.task.test.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderProductResponse {
    private final long id;
    private final ProductResponse product;
    private final int quantity;

    public static OrderProductResponse from(final OrderProduct order) {
        ProductResponse productResponse = ProductResponse.from(order.getProduct());
        return new OrderProductResponse(order.getId(), productResponse, order.getQuantity());
    }
}
