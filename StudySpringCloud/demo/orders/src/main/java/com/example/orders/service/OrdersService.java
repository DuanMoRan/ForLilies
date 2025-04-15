package com.example.orders.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.orders.entity.MyPage;
import com.example.orders.entity.Orders;

public interface OrdersService {
    Integer insert(Orders orders);
    Page<Orders> find(Orders orders , MyPage myPage);
    Integer update(Orders orders);
    Integer delete(Orders orders);
}
