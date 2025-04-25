package com.example.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.goods.entity.Goods;
import com.example.goods.entity.MyPage;
import com.example.goods.mapper.GoodsMapper;
import com.example.goods.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper mapper;

    @Override
    public Integer insert(Goods goods) {
        Goods res = mapper.selectById(goods.getId());
        if (res == null) return mapper.insert(goods);
        return -1;
    }

    @Override
    public Page<Goods> getGoods(Goods goods, MyPage myPage) {
        Page<Goods> page = new Page<>(myPage.getPages(), myPage.getSize());
        // 全查询
        if (goods == null)
            return mapper.selectPage(page,  Wrappers.<Goods>query().orderByAsc("id"));
            

        List<Goods> res = new ArrayList<>();

        // 根据id查询
        Goods getById = mapper.selectById(goods.getId());
        if (getById != null) {
            res.add(getById);
            page.setRecords(res);
            page.setTotal(res.size());
            return page;
        }

        // 根据名字查询
        Goods getByName = mapper.selectOne(new QueryWrapper<Goods>()
                .eq("name", goods.getName()));
        if (getByName != null) {
            res.add(getByName);
            page.setRecords(res);
            page.setTotal(res.size());
            return page;
        }

        // 根据价格查询
        res = mapper.selectList(new QueryWrapper<Goods>()
                .eq("price", goods.getPrice()));
        if (res != null) {
            page.setRecords(res);
            page.setTotal(res.size());
            return page;
        }

        // 根据类型查询
        res = mapper.selectList(new QueryWrapper<Goods>()
                .eq("type", goods.getType()));

        if (res != null) {
            page.setRecords(res);
            page.setTotal(res.size());
            return page;
        }

        return null;
    }

    @Override
    public Integer update(Goods goods) {
        if(mapper.selectById(goods.getId()) != null)
            return mapper.updateById(goods);
        return -1;    
    }

    @Override
    public Integer delete(Goods goods) {
        if(mapper.selectById(goods.getId()) != null)
            return mapper.deleteById(goods);
        return -1;
    }

}
