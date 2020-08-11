package com.shop.rest.dto.product_order;

import lombok.Data;

import java.util.List;

@Data
public class EditedProductOrderDTO {
    private Long id;
    private List<OrderedProductOrderDTO> productOrders;
}
