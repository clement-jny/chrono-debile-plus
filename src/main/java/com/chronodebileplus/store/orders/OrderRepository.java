package com.chronodebileplus.store.orders;

import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderKey> {
    @Transactional
    void deleteByOrderKey_Id(Long id);

    List<Order> findByOrderKey_Id(Long id);
}
