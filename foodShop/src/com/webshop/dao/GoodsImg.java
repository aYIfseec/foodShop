package com.webshop.dao;

public interface GoodsImg {
    int deleteByPrimaryKey(Integer goodsImgId);

    int insert(GoodsImg record);

    int insertSelective(GoodsImg record);

    GoodsImg selectByPrimaryKey(Integer goodsImgId);

    int updateByPrimaryKeySelective(GoodsImg record);

    int updateByPrimaryKey(GoodsImg record);
}