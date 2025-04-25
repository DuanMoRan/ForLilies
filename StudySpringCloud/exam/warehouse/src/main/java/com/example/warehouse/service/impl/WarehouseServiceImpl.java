package com.example.warehouse.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.entity.MyPage;
import com.example.warehouse.mapper.WarehouseMapper;
import com.example.warehouse.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseMapper mapper;

    @Override
    public Integer insert(Warehouse warehouse) {
        Warehouse res = mapper.selectById(warehouse.getId());
        if (res == null) return mapper.insert(warehouse);
        return -1;
    }

    @Override
    public Page<Warehouse> getWarehouse(Warehouse warehouse, MyPage myPage) {
        Page<Warehouse> page = new Page<>(myPage.getPages(), myPage.getSize());
        // 全查询
        if (warehouse == null)
            return mapper.selectPage(page, Wrappers.<Warehouse>query().orderByAsc("id"));

        List<Warehouse> res = new ArrayList<>();

        // 根据id查询
        Warehouse getById = mapper.selectById(warehouse.getId());
        if (getById != null) {
            res.add(getById);
            page.setRecords(res);
            page.setTotal(res.size());
            return page;
        }

        // 根据名字查询
        Warehouse getByName = mapper.selectOne(new QueryWrapper<Warehouse>()
                .eq("name", warehouse.getName()));
        if (getByName != null) {
            res.add(getByName);
            page.setRecords(res);
            page.setTotal(res.size());
            return page;
        }

        return null;
    }

    @Override
    public Integer update(Warehouse warehouse) {
        if(mapper.selectById(warehouse.getId()) != null)
            return mapper.updateById(warehouse);
        return -1;    
    }

    @Override
    public Integer delete(Warehouse warehouse) {
        if(mapper.selectById(warehouse.getId()) != null)
            return mapper.deleteById(warehouse);
        return -1;
    }

}
