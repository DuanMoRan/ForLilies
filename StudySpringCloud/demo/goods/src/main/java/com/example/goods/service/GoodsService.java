package com.example.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.goods.entity.Goods;
import com.example.goods.entity.MyPage;

public interface GoodsService {
    Integer insert(Goods goods);
    Page<Goods> getGoods(Goods goods , MyPage myPage);
    Integer update(Goods goods);
    Integer delete(Goods goods);
}
