package learn.subject.shop.infra.repository;

import learn.subject.shop.domain.Order;
import learn.subject.shop.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrder(Order order);
}
