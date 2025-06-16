package com.task.test.order.service;

import com.task.test.order.dto.OrderCreateRequest;
import com.task.test.order.dto.OrderResponse;
import com.task.test.order.entity.Order;
import com.task.test.order.repository.OrderRepository;
import com.task.test.product.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderRetrieveServiceTest {

    @InjectMocks
    private OrderRetrieveService orderRetrieveService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    @DisplayName("전체 주문 조회 테스트")
    void findAllTest() {
        //given
        Product product1 = Product.create("apple", 1000, 5);
        Product product2 = Product.create("banana", 2000, 10);
        Product product3 = Product.create("orange", 3000, 15);

        OrderCreateRequest request1 = OrderCreateRequest.of(product1, 3);
        OrderCreateRequest request2 = OrderCreateRequest.of(product2, 1);
        OrderCreateRequest request3 = OrderCreateRequest.of(product3, 2);

        Order order1 = Order.create(List.of(request1, request2));
        Order order2 = Order.create(List.of(request2, request3));
        Order order3 = Order.create(List.of(request1, request2, request3));

        when(orderRepository.findAll()).thenReturn(List.of(order1, order2, order3));

        //when
        List<OrderResponse> result = orderRetrieveService.findAll();

        //then
        assertThat(result)
                .hasSize(3)
                .extracting(response -> response.getOrderProducts().size(), OrderResponse::getTotalPrice)
                .containsExactlyInAnyOrder(
                        tuple(2, 5000),
                        tuple(2, 8000),
                        tuple(3, 11000)
                );
    }
}
