package com.webshop.service;

import java.util.List;

import com.webshop.entity.Order;

/**
 * @author 黄勇康
 * @date   2017
 */
public interface OrderService {

	/**
	 * @param order
	 * @return
	 */
	boolean addOrder(Order order);

	/**
	 * @param goodsIdArray
	 * @param goodsBuyNum
	 * @return
	 */
	boolean addOrderChildTable(String userId, String orderid, String[] goodsIdArray, String[] goodsBuyNum, String[] isChoose);

	/**
	 * @param userId
	 * @return
	 */
	List<Order> getOrderByUserId(String userId);

}
