package com.webshop.entity;

public class GoodsImg {
    private Integer goodsImgId;

    private String goodsId;

    private String goodsImgUrl;

    private int goodsImgType;

    public Integer getGoodsImgId() {
        return goodsImgId;
    }

    public void setGoodsImgId(Integer goodsImgId) {
        this.goodsImgId = goodsImgId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getGoodsImgUrl() {
        return goodsImgUrl;
    }

    public void setGoodsImgUrl(String goodsImgUrl) {
        this.goodsImgUrl = goodsImgUrl == null ? null : goodsImgUrl.trim();
    }

    public int getGoodsImgType() {
        return goodsImgType;
    }

    public void setGoodsImgType(int goodsImgType) {
        this.goodsImgType = goodsImgType;
    }
}