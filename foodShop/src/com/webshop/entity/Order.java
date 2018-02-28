package com.webshop.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String orderId;

    private String userId;

    private Date orderTime;
    
    private String addressId;
    
    private Integer id;

    private String goodsId;
    
    private String goodsName;

    private String goodsImg;
    
    private BigDecimal goodsPrice;
    
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

	/**
	 * @return the goodsImg
	 */
	public String getGoodsImg() {
		return goodsImg;
	}

	/**
	 * @param goodsImg the goodsImg to set
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

	private String userName;
    private String address;

    /**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the goodsId
	 */
	public String getGoodsId() {
		return goodsId;
	}

	/**
	 * @param goodsId the goodsId to set
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * @return the buyNum
	 */
	public Integer getBuyNum() {
		return buyNum;
	}

	/**
	 * @param buyNum the buyNum to set
	 */
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	private Integer buyNum;

    public Order() {}
    
    /**
	 * @param string
	 * @param userId2
	 * @param date
	 * @param addressId2
	 */
	public Order(String string, String userId2, Date date, String addressId2) {
		this.addressId = addressId2;
		this.orderId = string;
		this.userId = userId2;
		this.orderTime = date;
	}

	/**
	 * @return the addressId
	 */
	public String getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}