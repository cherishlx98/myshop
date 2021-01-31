package com.lx.order.api;

import com.lx.order.entity.Order;

import java.util.ArrayList;

public interface OrderService {
    Order getById(Long id);
    ArrayList<Order> getByUserId(Long userId, int offset, int size);
    Order create(Long goodId, Long userId, String contact, String address, String phone);
    String pay(Long id);
}
