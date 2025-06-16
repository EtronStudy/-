package com.task.test.order.service;

import com.task.test.order.dto.OrderResponse;
import com.task.test.order.entity.Order;
import com.task.test.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderRetrieveService {

    private final OrderRepository orderRepository;

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(OrderResponse::from)
                .toList();
    }

    public OrderResponse findOrderById(final Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("주문 정보를 찾을 수 없습니다."));

        return OrderResponse.from(order);
    }
}
