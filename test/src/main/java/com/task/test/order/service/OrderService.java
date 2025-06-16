package com.task.test.order.service;

import com.task.test.order.repository.OrderRepository;
import com.task.test.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public void createOrder() {
//        Product product = productRepository.findById()
//                .orElseThrow(() -> new RuntimeException("제품 정보를 찾을 수 없습니다."));
    }
}
