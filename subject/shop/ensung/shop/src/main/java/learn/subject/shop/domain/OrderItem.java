package learn.subject.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Order order;

    private int quantity;

    private int price;

    private OrderItem(Product product, Order order, int quantity) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.price = calculatePrice(product, quantity);
    }

    public static OrderItem of(Product product, Order order, int quantity) {
        return new OrderItem(product, order, quantity);
    }

    private int calculatePrice(Product product, int quantity) {
        return product.getPrice() * quantity;
    }
}
