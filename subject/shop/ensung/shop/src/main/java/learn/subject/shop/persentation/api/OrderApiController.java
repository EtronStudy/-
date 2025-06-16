package learn.subject.shop.persentation.api;


import learn.subject.shop.application.OrderService;
import learn.subject.shop.persentation.dto.request.CreateOrderRequest;
import learn.subject.shop.persentation.dto.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping("/api/v1/order/{memberId}")
    public ApiResponse<OrderResponse> getOrders(@PathVariable Long memberId) {
        return ApiResponse.success("0000", "주문 조회 성공", orderService.findById(memberId));
    }

    @PostMapping("/api/v1/order")
    public ApiResponse<Void> createOrder(@RequestBody CreateOrderRequest request) {
        orderService.save(request);
        return ApiResponse.success("0000", "주문 성공");
    }
}
