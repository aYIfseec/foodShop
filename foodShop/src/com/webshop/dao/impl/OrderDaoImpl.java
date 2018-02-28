package com.webshop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.webshop.dao.OrderDao;
import com.webshop.entity.Address;
import com.webshop.entity.Order;

/**
 * @author 黄勇康
 * @date   2017
 */
public class OrderDaoImpl implements OrderDao {

	
	public int deleteByPrimaryKey(String orderId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean insert(Order record) {
		String sql = "insert into t_order(orderId, userId, orderTime, addressId) values(?, ?, ?, ?)";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(record.getOrderId());
		parameters.add(record.getUserId());
		parameters.add(record.getOrderTime());
		parameters.add(record.getAddressId());
		return BaseDao.operUpdate(sql, parameters);
	}

	public int insertSelective(Order record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Order selectByPrimaryKey(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(Order record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Order record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Order> selectByUserId(String userId) {
		String sql = "select A.orderId, A.userId, B.userName, A.orderTime, C.address, E.goodsId, E.goodsName, D.buyNum, E.goodsPrice from " +
				"t_order as A, t_user as B, t_address as C, t_order_child as D, t_goods as E " +
				"where A.userId=? and A.orderId=D.orderId and A.userId=B.userId and A.addressId=C.addressId and D.goodsId=E.goodsId " +
				"order by orderTime desc";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(userId);
		List<Order> orderList = null;
		try {
			orderList = BaseDao.operQuery(sql, parameters, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}

}
