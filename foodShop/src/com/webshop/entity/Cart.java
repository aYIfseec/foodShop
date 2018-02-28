package com.webshop.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Cart  {
    private Integer cartId;

    private String userId;

    private String goodsId;

    private Integer buyNum;

    private Date addTime;

    public Cart() {}
    
    /**
	 * @param userId2
	 * @param goodsId2
	 * @param num
	 */
	public Cart(String userId2, String goodsId2, int num, Date time) {
		this.userId = userId2;
		this.goodsId = goodsId2;
		this.buyNum = num;
		this.addTime = time;
	}

	public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    
    
    //////////
    private String goodsName;

    private String goodsImg;
    
    private BigDecimal goodsPrice;

    private Integer goodsNum;

    private Integer salesNum;

    private String goodsSize;

    private String goodsFrom;

    private String goodsTime;

    private String goodsSaveCondition;

    private String goodsDescribe;

    private String goodsExplain;

    private String goodsClass;

    private BigDecimal goodsDiscount;

    private Date discountStartTime;

    private Date discountEndTime;
    /**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public String getGoodsImg() {
		return goodsImg;
	}

	/**
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	/**
	 * @return the goodsPrice
	 */
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	/**
	 * @param goodsPrice the goodsPrice to set
	 */
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	/**
	 * @return the goodsNum
	 */
	public Integer getGoodsNum() {
		return goodsNum;
	}

	/**
	 * @param goodsNum the goodsNum to set
	 */
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	/**
	 * @return the salesNum
	 */
	public Integer getSalesNum() {
		return salesNum;
	}

	/**
	 * @param salesNum the salesNum to set
	 */
	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	/**
	 * @return the goodsSize
	 */
	public String getGoodsSize() {
		return goodsSize;
	}

	/**
	 * @param goodsSize the goodsSize to set
	 */
	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	/**
	 * @return the goodsFrom
	 */
	public String getGoodsFrom() {
		return goodsFrom;
	}

	/**
	 * @param goodsFrom the goodsFrom to set
	 */
	public void setGoodsFrom(String goodsFrom) {
		this.goodsFrom = goodsFrom;
	}

	/**
	 * @return the goodsTime
	 */
	public String getGoodsTime() {
		return goodsTime;
	}

	/**
	 * @param goodsTime the goodsTime to set
	 */
	public void setGoodsTime(String goodsTime) {
		this.goodsTime = goodsTime;
	}

	/**
	 * @return the goodsSaveCondition
	 */
	public String getGoodsSaveCondition() {
		return goodsSaveCondition;
	}

	/**
	 * @param goodsSaveCondition the goodsSaveCondition to set
	 */
	public void setGoodsSaveCondition(String goodsSaveCondition) {
		this.goodsSaveCondition = goodsSaveCondition;
	}

	/**
	 * @return the goodsDescribe
	 */
	public String getGoodsDescribe() {
		return goodsDescribe;
	}

	/**
	 * @param goodsDescribe the goodsDescribe to set
	 */
	public void setGoodsDescribe(String goodsDescribe) {
		this.goodsDescribe = goodsDescribe;
	}

	/**
	 * @return the goodsExplain
	 */
	public String getGoodsExplain() {
		return goodsExplain;
	}

	/**
	 * @param goodsExplain the goodsExplain to set
	 */
	public void setGoodsExplain(String goodsExplain) {
		this.goodsExplain = goodsExplain;
	}

	/**
	 * @return the goodsClass
	 */
	public String getGoodsClass() {
		return goodsClass;
	}

	/**
	 * @param goodsClass the goodsClass to set
	 */
	public void setGoodsClass(String goodsClass) {
		this.goodsClass = goodsClass;
	}

	/**
	 * @return the goodsDiscount
	 */
	public BigDecimal getGoodsDiscount() {
		return goodsDiscount;
	}

	/**
	 * @param goodsDiscount the goodsDiscount to set
	 */
	public void setGoodsDiscount(BigDecimal goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
	}

	/**
	 * @return the discountStartTime
	 */
	public Date getDiscountStartTime() {
		return discountStartTime;
	}

	/**
	 * @param discountStartTime the discountStartTime to set
	 */
	public void setDiscountStartTime(Date discountStartTime) {
		this.discountStartTime = discountStartTime;
	}

	/**
	 * @return the discountEndTime
	 */
	public Date getDiscountEndTime() {
		return discountEndTime;
	}

	/**
	 * @param discountEndTime the discountEndTime to set
	 */
	public void setDiscountEndTime(Date discountEndTime) {
		this.discountEndTime = discountEndTime;
	}
}