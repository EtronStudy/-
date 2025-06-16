package learn.subject.shop.application;

import learn.subject.shop.domain.Member;
import learn.subject.shop.domain.Order;
import learn.subject.shop.domain.OrderItem;
import learn.subject.shop.domain.Product;
import learn.subject.shop.infra.repository.MemberRepository;
import learn.subject.shop.infra.repository.OrderItemRepository;
import learn.subject.shop.infra.repository.OrderRepository;
import learn.subject.shop.infra.repository.ProductRepository;
import learn.subject.shop.persentation.dto.OrderItemDto;
import learn.subject.shop.persentation.dto.request.CreateOrderRequest;
import learn.subject.shop.persentation.dto.request.ProductRequest;
import learn.subject.shop.persentation.dto.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public void save(CreateOrderRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자입니다."));

        Order order = Order.of(member);
        Order savedOrder = orderRepository.save(order);

        int totalPrice = 0;
        int quantity = 0;

        for (ProductRequest product : request.getProductIds()) {
            Product targetProduct = productRepository.findProductByIdWithPessimisticLock(product.getProductId());
            targetProduct.decreaseQuantity(product.getQuantity());

            OrderItem orderItem = OrderItem.of(targetProduct, savedOrder, product.getQuantity());
            orderItemRepository.save(orderItem);

            totalPrice += targetProduct.getPrice();
            quantity += product.getQuantity();
        }

        order.finishOrder(totalPrice, quantity);

    }

    @Transactional(readOnly = true)
    public OrderResponse findById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자입니다."));
        Order order = orderRepository.findByMember(member);
        List<OrderItem> orderItems = orderItemRepository.findByOrder(order);
        OrderResponse orderResponse = new OrderResponse(member.getId(), order.getId(), order.getOrderStatus());
        List<OrderItemDto> list = orderItems.stream().map(orderItem -> new OrderItemDto(orderItem.getProduct().getId(), orderItem.getQuantity())).toList();
        orderResponse.setOrderItems(list);
        return orderResponse;
    }
}
