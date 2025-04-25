package com.example.warehouse.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.entity.MyPage;

public interface WarehouseService {
    Integer insert(Warehouse warehouse);
    Page<Warehouse> getWarehouse(Warehouse warehouse , MyPage myPage);
    Integer update(Warehouse warehouse);
    Integer delete(Warehouse warehouse);
}
