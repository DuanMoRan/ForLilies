package com.example.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.orders.entity.MyPage;
import com.example.orders.entity.Orders;
import com.example.orders.entity.Query;
import com.example.orders.entity.ResponseData;
import com.example.orders.service.OrdersService;

@RestController("/orders")
public class OrdersContorller {
    
    @Autowired
    private OrdersService service;

    @PostMapping("/insert")
    public ResponseData insert(@RequestBody Orders orders) {
        ResponseData res = new ResponseData();
        Integer result = service.insert(orders);
        if(result == -1){
            res.setFlag(false);
            res.setMessage("插入失败");
        }else{
            res.setFlag(true);
            res.setMessage("插入成功");
        }

        return res;
    }

    @PostMapping("/find/all")
    public ResponseData findAll(@RequestBody MyPage myPage) {
        ResponseData res = new ResponseData();
        Page<Orders> list = service.find(null, myPage);
        if(list != null){
            res.setFlag(true);
            res.setMessage("查询成功");
            res.setData(list);
            return res;
        }
        res.setFlag(false);
        res.setMessage("查询失败");
        return res;
    }
    
    @PostMapping("/find/list")
    public ResponseData findList(@RequestBody Query query) {
        ResponseData res = new ResponseData();
        Page<Orders> list = service.find(query.getOrders(), query.getMyPage());
        if(list != null){
            res.setFlag(true);
            res.setMessage("查询成功");
            res.setData(list);
            return res;
        }
        res.setFlag(false);
        res.setMessage("查询失败");
        return res;
    }

    @PostMapping("/find/one")
    public ResponseData findOne(@RequestBody Orders orders) {
        ResponseData res = new ResponseData();
        MyPage myPage = new MyPage(1 , 1);

        Page<Orders> list = service.find(orders , myPage);
        if(list != null){
            res.setFlag(true);
            res.setMessage("查询成功");
            res.setData(list);
            return res;
        }
        res.setFlag(false);
        res.setMessage("查询失败");
        return res;
    }

    @PostMapping("/update")
    public ResponseData update(@RequestBody Orders Orders) {
        ResponseData res = new ResponseData();
        Integer result = service.update(Orders);
        if(result == -1){
            res.setFlag(false);
            res.setMessage("修改失败");
        }else{
            res.setFlag(true);
            res.setMessage("修改成功");
        }

        return res;
    }
}
