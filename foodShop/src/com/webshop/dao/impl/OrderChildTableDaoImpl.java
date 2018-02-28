package com.webshop.dao.impl;

import com.webshop.dao.OrderChildTableDao;
import com.webshop.entity.OrderChildTable;

/**
 * @author 黄勇康
 * @date   2017
 */
public class OrderChildTableDaoImpl implements OrderChildTableDao {

	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(OrderChildTable record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(OrderChildTable record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public OrderChildTable selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(OrderChildTable record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(OrderChildTable record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean insert(String val) {
		String sql = "insert into t_order_child (orderId, goodsId, buyNum) values ";
		sql += val;
		return BaseDao.operUpdate(sql, null);
	}

}
