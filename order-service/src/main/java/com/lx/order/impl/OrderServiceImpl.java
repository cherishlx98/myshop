package com.lx.order.impl;

import com.lx.order.api.OrderService;
import com.lx.order.entity.Order;
import com.lx.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;


    public Order getById(Long id) {
        return orderMapper.getById(id);
    }

    public ArrayList<Order> getByUserId(Long userId, int offset, int size) {
        return orderMapper.getByUserId(userId, offset, size);
    }

    public Order create(Long goodId, Long userId, String contact, String address, String phone) {
        //1.先创建一个order
        if (contact == null || contact.length() == 0 || address == null || address.length() == 0 || phone == null || phone.length() == 0) {
            return null;
        }
        Order newOrder = new Order(goodId, null, userId, System.currentTimeMillis(), contact, address, phone);
        //2.再根据这个order插入数据库，insert之后会把id属性设置好，@Options注解的作用
        orderMapper.insert(newOrder);
        System.out.println(newOrder.getId());
        return newOrder;
    }

    /**
     * @Transactional是基于spring aop,JDBC数据库事务
     * @param id
     * @return payId
     */
    @Transactional
    public String pay(Long id) {
        //明天可以试一试超时的情况是否会出现重复订单的情况
        Order order = orderMapper.getById(id);
        if (order == null){
            return null;
        }
        //就是在这里超时
        if(order.getPayId() != null){
            return order.getPayId();
        }
        String payId;
        String str = (order.toString() + new Random().nextInt());
        //其实可以封装一个工具类
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());

            byte[] bs = md.digest();

            StringBuilder res = new StringBuilder();
            for (byte b : bs) {
                res.append(String.format("%02X", b));
            }
            payId = res.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        //订单支付，修改订单状态
        order.setPayId(payId);
        orderMapper.update(order);

        return payId;
    }
}
