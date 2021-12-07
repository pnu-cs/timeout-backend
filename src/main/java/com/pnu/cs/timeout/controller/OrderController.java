package com.pnu.cs.timeout.controller;

import com.pnu.cs.timeout.dto.OrderDto;
import com.pnu.cs.timeout.dto.OrderTransformer;
import com.pnu.cs.timeout.model.Order;
import com.pnu.cs.timeout.service.OrderService;
import com.pnu.cs.timeout.service.ProductService;
import com.pnu.cs.timeout.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addOrder(@RequestBody OrderDto orderDto) {
        Order order = orderService.create(
                OrderTransformer.toEntity(orderDto, userService, productService)
        );

        return new ResponseEntity<>(order.getId(), HttpStatus.OK);
    }
}
