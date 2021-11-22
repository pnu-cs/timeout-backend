package com.pnu.cs.timeout.service.impl;

import com.pnu.cs.timeout.model.Order;
import com.pnu.cs.timeout.repository.OrderRepository;
import com.pnu.cs.timeout.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order order) {
        if (order != null) {
            orderRepository.save(order);
        }
        return order;
    }

    @Override
    public Order readById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order update(Order order) {
        if (order != null) {
            if (orderRepository.existsById(order.getId())) {
                return orderRepository.save(order);
            }
        }

        return null;
    }

    @Override
    public void delete(long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.isEmpty() ? new ArrayList<>() : orders;
    }
}
