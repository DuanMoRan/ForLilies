package com.example.goods.controller;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.goods.entity.Goods;
import com.example.goods.entity.MyPage;
import com.example.goods.entity.Query;
import com.example.goods.entity.ResponseData;
import com.example.goods.service.GoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController()
public class GoodsController {

    @Autowired
    private GoodsService service;

    @PostMapping("/insert")
    public ResponseData insert(@RequestBody Goods goods) {
        ResponseData res = new ResponseData();
        Integer result = service.insert(goods);
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
        Page<Goods> list = service.getGoods(null, myPage);
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
        Page<Goods> list = service.getGoods(query.getGoods(), query.getMyPage());
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
    public ResponseData findOne(@RequestBody Goods goods) {
        ResponseData res = new ResponseData();
        MyPage myPage = new MyPage(1, 1);
        Page<Goods> list = service.getGoods(goods , myPage);
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
    public ResponseData update(@RequestBody Goods goods) {
        ResponseData res = new ResponseData();
        Integer result = service.update(goods);
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
