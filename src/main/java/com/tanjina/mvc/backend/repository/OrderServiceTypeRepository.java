package com.tanjina.mvc.backend.repository;

import com.tanjina.mvc.backend.entity.Order;
import com.tanjina.mvc.backend.entity.OrderServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OrderServiceTypeRepository extends JpaRepository<OrderServiceType, Integer> {

    // ✅ This will fix your red underline
    Optional<OrderServiceType> findByOrder(Order order);
}
