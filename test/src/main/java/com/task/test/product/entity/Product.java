package com.task.test.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;

    private Product(final String name, final Integer price, final Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static Product create(final String name, final Integer price, final Integer quantity) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("제품 이름은 필수값입니다.");
        }

        if (price == null) {
            throw new IllegalArgumentException("제품 가격은 필수값입니다.");
        }

        if (price < 0) {
            throw new IllegalArgumentException("제품 가격은 0 이상이어야합니다.");
        }

        if (quantity == null) {
            throw new IllegalArgumentException("제품 수량은 필수값입니다.");
        }

        if (quantity < 0) {
            throw new IllegalArgumentException("제품 수량은 0 이상이어야합니다.");
        }

        return new Product(name, price, quantity);
    }

    public void deductQuantity(final int quantity) {
        if (this.quantity < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.quantity -= quantity;
    }
}
