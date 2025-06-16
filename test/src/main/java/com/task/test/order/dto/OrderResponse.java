package com.task.test.order.dto;

import com.task.test.order.entity.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class OrderResponse {
    private final long id;
    private final List<OrderProductResponse> orderProducts;
    private final int totalPrice;

    public static OrderResponse from(final Order order) {
        List<OrderProductResponse> orderProductResponses = order.getOrderProducts().stream()
                .map(OrderProductResponse::from)
                .toList();

        return new OrderResponse(order.getId(), orderProductResponses, order.getTotalPrice());
    }
}
