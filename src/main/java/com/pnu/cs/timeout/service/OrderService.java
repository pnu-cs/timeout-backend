package com.pnu.cs.timeout.service;

import com.pnu.cs.timeout.model.Order;

import java.util.List;

public interface OrderService {
    Order create(Order order);
    Order readById(long id);
    Order update(Order order);
    void delete(long id);
    List<Order> getAll();
}
