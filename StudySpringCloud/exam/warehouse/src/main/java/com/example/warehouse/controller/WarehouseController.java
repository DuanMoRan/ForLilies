package com.example.warehouse.controller;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.entity.MyPage;
import com.example.warehouse.entity.Query;
import com.example.warehouse.entity.ResponseData;
import com.example.warehouse.service.WarehouseService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService service;

    @PostMapping("/insert")
    public ResponseData insert(@RequestBody Warehouse warehouse) {
        ResponseData res = new ResponseData();
        Integer result = service.insert(warehouse);
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
        Page<Warehouse> list = service.getWarehouse(null, myPage);
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
        Page<Warehouse> list = service.getWarehouse(query.getWarehouse(), query.getMyPage());
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
    public ResponseData findOne(@RequestBody Warehouse warehouse) {
        ResponseData res = new ResponseData();
        MyPage myPage = new MyPage(1, 1);
        Page<Warehouse> list = service.getWarehouse(warehouse , myPage);
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
    public ResponseData update(@RequestBody Warehouse warehouse) {
        ResponseData res = new ResponseData();
        Integer result = service.update(warehouse);
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
