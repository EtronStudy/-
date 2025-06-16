package com.task.test.order.repository;

import com.task.test.order.dto.OrderCreateRequest;
import com.task.test.order.entity.Order;
import com.task.test.product.entity.Product;
import com.task.test.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@ActiveProfiles("test")
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("주문 조회 테스트")
    void findAllTest() {
        //given
        Product product1 = Product.create("apple", 1000, 5);
        Product product2 = Product.create("banana", 2000, 10);
        Product product3 = Product.create("orange", 3000, 15);

        productRepository.saveAll(List.of(product1, product2, product3));

        OrderCreateRequest request1 = OrderCreateRequest.of(product1, 3);
        OrderCreateRequest request2 = OrderCreateRequest.of(product2, 1);
        OrderCreateRequest request3 = OrderCreateRequest.of(product3, 2);

        Order order1 = Order.create(List.of(request1, request2));
        Order order2 = Order.create(List.of(request2, request3));

        orderRepository.saveAll(List.of(order1, order2));

        //when
        List<Order> result = orderRepository.findAll();

        //then
        assertThat(result)
                .hasSize(2)
                .extracting(order -> order.getOrderProducts().size(), Order::getTotalPrice)
                .containsExactlyInAnyOrder(
                        tuple(2, 5000),
                        tuple(2, 8000)
                );
    }
}
