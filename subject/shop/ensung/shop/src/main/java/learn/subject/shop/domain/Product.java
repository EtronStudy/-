package learn.subject.shop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import learn.subject.shop.persentation.dto.request.CreateProductRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int quantity;

    private int price;

    private Product(String name, String description, int quantity, int price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public static Product create(String name, String description, int quantity, int price) {
        return new Product(name, description, quantity, price);
    }

    public static Product toEntity(CreateProductRequest request) {
        return new Product(request.getName(), request.getDescription(), request.getQuantity(), request.getPrice());
    }

    public void decreaseQuantity(int quantity) {

        if (this.quantity <= 0) {
            throw new IllegalArgumentException("재고가 존재하지 않습니다.");
        }

        this.quantity -= quantity;

        if (this.quantity < 0) {
            throw new IllegalArgumentException("주문 수가 제고 량보다 초과했습니다. 다시 시도해주세요");
        }
    }
}
