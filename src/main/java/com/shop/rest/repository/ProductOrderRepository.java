package com.shop.rest.repository;

import com.shop.rest.model.Order;
import com.shop.rest.model.ProductOrder;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository
  extends CrudRepository<ProductOrder, Long> {
  @Query("SELECT po FROM ProductOrder po WHERE po.product.id = :id")
  List<ProductOrder> findAllByProduct_Id(Long id);

  @Query("SELECT po FROM ProductOrder po WHERE po.order.id = :id")
  List<ProductOrder> findAllByOrder_Id(Long id);

  @Query("SELECT po FROM ProductOrder po WHERE po.order.id IN :ids")
  List<ProductOrder> findAllByOrdersIdIn(List<Long> ids);
}
