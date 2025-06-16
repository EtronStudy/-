package learn.subject.shop.application;

import learn.subject.shop.domain.Product;
import learn.subject.shop.infra.repository.OrderRepository;
import learn.subject.shop.infra.repository.ProductRepository;
import learn.subject.shop.persentation.dto.request.CreateOrderRequest;
import learn.subject.shop.persentation.dto.request.ProductRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Test
    @DisplayName("동시성을 활용하여 주문 제고를 체크한다.")
    void createOrderMulti() throws InterruptedException {
        // Given
        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        List<ProductRequest> productRequests = new ArrayList<>();
        productRequests.add(new ProductRequest(1L, 1));
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(1L, productRequests);

        for (int i = 0; i <= threadCount; i++) {
            executorService.submit(() -> {
                try {
                    orderService.save(createOrderRequest);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        // Then - 재고 검증
        Product product1 = productRepository.findById(1).get();

        assertThat(product1.getQuantity()).isEqualTo(0);
    }

    @Test
    @DisplayName(("수량을 넘은 주문을 했을 경우 예외를 발생한다."))
    void exceptionOrder(){
        List<ProductRequest> productRequests = new ArrayList<>();
        productRequests.add(new ProductRequest(2L, 40));
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(1L, productRequests);
        assertThatThrownBy(()->orderService.save(createOrderRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 수가 제고 량보다 초과했습니다. 다시 시도해주세요");
    }

}