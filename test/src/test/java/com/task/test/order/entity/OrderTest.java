package com.task.test.order.entity;

import com.task.test.order.dto.OrderCreateRequest;
import com.task.test.product.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

class OrderTest {

    @Test
    @DisplayName("주문 생성 테스트")
    void createTest() {
        //given
        Product product1 = Product.create("apple", 1000, 5);
        Product product2 = Product.create("banana", 2000, 5);
        OrderCreateRequest request1 = OrderCreateRequest.of(product1, 3);
        OrderCreateRequest request2 = OrderCreateRequest.of(product2, 1);

        //when
        Order result = Order.create(List.of(request1, request2));

        //then
        assertThat(result.getOrderProducts())
                .hasSize(2)
                .extracting(OrderProduct::getProduct, OrderProduct::getQuantity)
                .containsExactly(
                        tuple(product1, 3),
                        tuple(product2, 1)
                );
        assertThat(result.getTotalPrice())
                .isEqualTo(5000);
    }
}
