package com.wak.eatsmeet.repository.order;

import com.wak.eatsmeet.model.order.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItems, Integer> {
}
