package com.webshop.dao;

import java.util.List;

import com.webshop.entity.Cart;
import com.webshop.entity.Goods;

public interface CartDao {
    int deleteByPrimaryKey(Integer cartId);

    int insert(Cart record);

    boolean insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer cartId);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

	/**
	 * @param userId
	 * @return
	 */
	List<Cart> selectByUserId(String userId);

	/**
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	boolean updateByUserIdAndGoodsId(String userId, String goodsId, int num);

	/**
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	boolean deleteByUserIdAndGoodsId(String userId, String goodsId);

	/**
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	Cart selectByUserIdAndGoodsId(String userId, String goodsId);
}