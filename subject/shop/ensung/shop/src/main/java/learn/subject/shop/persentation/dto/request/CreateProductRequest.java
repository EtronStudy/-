package learn.subject.shop.persentation.dto.request;

import lombok.Getter;

@Getter
public class CreateProductRequest {
    private String name;
    private String description;
    private int quantity;
    private int price;
}
