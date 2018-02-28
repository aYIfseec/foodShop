package com.webshop.dao;

import com.webshop.entity.OrderChildTable;

public interface OrderChildTableDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderChildTable record);

    int insertSelective(OrderChildTable record);

    OrderChildTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderChildTable record);

    int updateByPrimaryKey(OrderChildTable record);

	/**
	 * @param val
	 * @return
	 */
	boolean insert(String val);
}