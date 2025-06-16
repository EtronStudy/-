package learn.subject.shop.infra.repository;

import learn.subject.shop.domain.Member;
import learn.subject.shop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findByMember(Member member);
}
