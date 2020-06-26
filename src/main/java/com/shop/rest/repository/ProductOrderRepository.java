package com.shop.rest.repository;

import com.shop.rest.model.ProductOrder;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository
  extends CrudRepository<ProductOrder, Long> {
  List<ProductOrder> findAllByProduct_Id(Long id);

  List<ProductOrder> findAllByOrder_IdIn(List<Long> ids);
}
