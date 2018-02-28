package com.webshop.service;

import java.util.List;

import com.webshop.entity.Address;
import com.webshop.entity.Cart;
import com.webshop.entity.Goods;

/**
 * @author 黄勇康
 * @date   2017
 */
public interface CartService {
	
	public List<Cart> getGoodsByUserId(String userId);

	/**
	 * @param goodsIdList
	 * @return
	 */
	public List<Cart> getGoodsByGoodsIdList(List<String> goodsIdList);

	/**
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	public boolean deleteGoodsFromCart(String userId, String goodsId);

	/**
	 * @param userId
	 * @param goodsId
	 * @param buyNum
	 * @return
	 */
	public boolean updateBuyNumForGoods(String userId, String goodsId,
			int buyNum);

	/**
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	public Cart getCartGoodsByUserIdAndGoodsId(String userId, String goodsId);

	/**
	 * @param userId
	 * @param goodsId
	 * @param num
	 * @return
	 */
	public boolean addGoodsToCart(String userId, String goodsId, int num);

	
	
}
