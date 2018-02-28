package com.webshop.dao;

import java.util.List;

import com.webshop.entity.Order;

public interface OrderDao {
    int deleteByPrimaryKey(String orderId);

    boolean insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

	/**
	 * @param userId
	 * @return
	 */
	List<Order> selectByUserId(String userId);
}