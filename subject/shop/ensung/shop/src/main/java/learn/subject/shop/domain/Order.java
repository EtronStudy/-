package learn.subject.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    private int quantity;

    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Order(Member member) {
        this.member = member;
        this.orderStatus = OrderStatus.READY;
    }

    public static Order of(Member member) {
        return new Order(member);
    }

    public void finishOrder(int totalPrice, int quantity) {
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.orderStatus = OrderStatus.FINISHED;
    }
}
