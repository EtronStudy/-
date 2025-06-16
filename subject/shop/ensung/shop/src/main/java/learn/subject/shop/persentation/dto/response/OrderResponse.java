package learn.subject.shop.persentation.dto.response;

import learn.subject.shop.domain.OrderStatus;
import learn.subject.shop.persentation.dto.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponse {

    private Long memberId;
    private Long orderId;
    private OrderStatus orderStatus;
    private List<OrderItemDto> orderItems;

    public OrderResponse(Long memberId, Long orderId, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.orderStatus = orderStatus;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;

    }
}
