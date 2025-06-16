package learn.subject.shop.persentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemDto {
    private Long productId;
    private int quantity;
}
