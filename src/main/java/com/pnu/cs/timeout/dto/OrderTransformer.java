package com.pnu.cs.timeout.dto;

import com.pnu.cs.timeout.model.Order;
import com.pnu.cs.timeout.model.OrderDetails;
import com.pnu.cs.timeout.service.ProductService;
import com.pnu.cs.timeout.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class OrderTransformer {
    public static Order toEntity(OrderDto dto, UserService userService, ProductService productService) {
        List<OrderDetails> orderDetailsListFromDto = new ArrayList<>();
        for (OrderDetailsDto orderDetailsDto : dto.getOrderDetailsDtoList()) {
            orderDetailsListFromDto.add(OrderDetailsTransformer.toEntity(orderDetailsDto, productService));
        }
        return Order.builder()
                .customer(userService.readById(dto.getUserId()))
                .creationDateTime(dto.getCreatedAt())
                .orderDetailsList(orderDetailsListFromDto)
                .build();
    }
}
