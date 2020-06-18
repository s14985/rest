package com.shop.rest.repository;

import com.shop.rest.model.Order;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
  List<Order> findAllByUser(Long id);
}
