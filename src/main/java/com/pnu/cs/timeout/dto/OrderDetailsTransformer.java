package com.pnu.cs.timeout.dto;

import com.pnu.cs.timeout.model.OrderDetails;
import com.pnu.cs.timeout.service.ProductService;

public class OrderDetailsTransformer {
    public static OrderDetails toEntity(OrderDetailsDto dto, ProductService productService) {
        return OrderDetails.builder()
                .product(productService.readById(dto.getProductId()))
                .quantity(dto.getQuantity())
                .build();
    }
}
