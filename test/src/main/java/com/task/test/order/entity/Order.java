package com.task.test.order.entity;

import com.task.test.order.dto.OrderCreateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    private Integer totalPrice;

    private Order(final List<OrderCreateRequest> requests) {
        this.orderProducts = requests.stream()
                .map(request -> OrderProduct.create(request.getProduct(), this, request.getQuantity()))
                .toList();
        this.totalPrice = orderProducts.stream()
                .mapToInt(OrderProduct::getPrice)
                .sum();
    }

    public static Order create(final List<OrderCreateRequest> requests) {
        return new Order(requests);
    }
}
