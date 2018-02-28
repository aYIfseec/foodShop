package com.webshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.webshop.dao.CartDao;
import com.webshop.dao.impl.BaseDao;
import com.webshop.entity.Cart;
import com.webshop.entity.Goods;

/**
 * @author 黄勇康
 * @date   2017
 */
public class CartDaoImpl implements CartDao {

	public int deleteByPrimaryKey(Integer cartId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Cart record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean insertSelective(Cart record) {
		String sql = "insert into t_cart(userId, goodsId, buyNum, addTime) values(?, ?, ?, ?)";
		List<Object> paramenters = new ArrayList<Object>();
		paramenters.add(record.getUserId());
		paramenters.add(record.getGoodsId());
		paramenters.add(record.getBuyNum());
		paramenters.add(record.getAddTime());
		return BaseDao.operUpdate(sql, paramenters);
	}

	public Cart selectByPrimaryKey(Integer cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(Cart record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Cart record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Cart> selectByUserId(String userId) {
		String sql = "select * from t_cart as a, t_goods as b where a.goodsId = b.goodsId and a.userId = ?";
		List<Cart> goodsList = null;
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(userId);
		try {
			goodsList = BaseDao.operQuery(sql, parameters, Cart.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsList;
	}

	public boolean updateByUserIdAndGoodsId(String userId, String goodsId, int num) {
		String sql = "update t_cart set buyNum=? where userId=? and goodsId=?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(num);
		parameters.add(userId);
		parameters.add(goodsId);
		boolean res = false;
		res = BaseDao.operUpdate(sql, parameters);
		return res;
	}

	public boolean deleteByUserIdAndGoodsId(String userId, String goodsId) {
		String sql = "delete from t_cart where userId=? and goodsId=?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(userId);
		parameters.add(goodsId);
		boolean res = false;
		res = BaseDao.operUpdate(sql, parameters);
		return res;
	}

	public Cart selectByUserIdAndGoodsId(String userId, String goodsId) {
		String sql = "select * from t_cart where userId = ? and goodsId = ?";
		List<Cart> goodsList = null;
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(userId);
		parameters.add(goodsId);
		try {
			goodsList = BaseDao.operQuery(sql, parameters, Cart.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsList.size() == 0 ? null : goodsList.get(0);
	}

}
