package com.task.test.order.entity;

import com.task.test.product.entity.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderProduct {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    private Integer quantity;

    private OrderProduct(final Product product, final Order order, final Integer quantity) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }

    public static OrderProduct create(final Product product, final Order order, final Integer quantity) {
        if (product == null) {
            throw new IllegalArgumentException("주문 상품은 필수값입니다.");
        }

        if (order == null) {
            throw new IllegalArgumentException("주문 정보는 필수값입니다.");
        }

        if (quantity == null) {
            throw new IllegalArgumentException("주문 수량은 필수값입니다.");
        }

        if (quantity < 0) {
            throw new IllegalArgumentException("주문 수량은 0 이상이어야합니다.");
        }

        return new OrderProduct(product, order, quantity);
    }

    public int getPrice() {
        return product.getPrice() * quantity;
    }
}
