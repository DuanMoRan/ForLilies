package com.example.orders.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.orders.entity.MyPage;
import com.example.orders.entity.Orders;
import com.example.orders.mapper.OrdersMapper;
import com.example.orders.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    private OrdersMapper mapper;

    @Override
    public Integer insert(Orders orders) {
        Orders res = mapper.selectById(orders.getId());
        if (res == null) return mapper.insert(orders);
        return -1;
    }

    @Override
    public Page<Orders> find(Orders orders , MyPage myPage) {
        Page<Orders> page = new Page<>(myPage.getPages(), myPage.getSize());
        // 全查询
        if (orders.getId() == null)
            return mapper.selectPage(page, null);

        List<Orders> res = new ArrayList<>();

        // 根据id查询
        Orders getById = mapper.selectById(orders.getId());
        if (getById != null) {
            res.add(getById);
            page.setRecords(res);
            page.setTotal(res.size());
            return page;
        }

        // 根据产品id查询
        Orders getByName = mapper.selectOne(new QueryWrapper<Orders>()
                .eq("product_id", orders.getProductId()));
        if (getByName != null) {
            res.add(getByName);
            page.setRecords(res);
            page.setTotal(res.size());
            return page;
        }

        // 根据数量查询
        res = mapper.selectList(new QueryWrapper<Orders>()
                .eq("count", orders.getCount()));
        if (res != null) {
            page.setRecords(res);
            page.setTotal(res.size());
            return page;
        }

        // 根据日期查询
        res = mapper.selectList(new QueryWrapper<Orders>()
                .eq("date", orders.getDate()));

        if (res != null) {
            page.setRecords(res);
            page.setTotal(res.size());
            return page;
        }

        return null;
    }

    @Override
    public Integer update(Orders orders) {
        if(mapper.selectById(orders.getId()) != null)
            return mapper.updateById(orders);
        return -1;
    }

    @Override
    public Integer delete(Orders orders) {
        if(mapper.selectById(orders.getId()) != null)
            return mapper.deleteById(orders);
        return -1;
    }
    
}
