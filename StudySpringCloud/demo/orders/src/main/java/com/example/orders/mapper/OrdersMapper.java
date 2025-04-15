package com.example.orders.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.orders.entity.Orders;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders>{
    
}
