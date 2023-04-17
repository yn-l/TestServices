package com.lebedev.test.Orders.Repository;

import com.lebedev.test.Orders.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "SELECT nextval('orders_order_id_seq');", nativeQuery = true)
    Long getNextValueFromSequence();
}
