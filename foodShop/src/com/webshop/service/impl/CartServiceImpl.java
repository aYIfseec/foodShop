package com.webshop.service.impl;

import java.util.Date;
import java.util.List;

import com.webshop.dao.CartDao;
import com.webshop.entity.Cart;
import com.webshop.service.CartService;

/**
 * @author 黄勇康
 * @date   2017
 */
public class CartServiceImpl implements CartService {

	private CartDao cartDao = new CartDaoImpl();
	
	/*public static void main(String agrs[]) {
		System.out.println(cartDao.selectByUserId("15179675005"));
	}*/
	
	public List<Cart> getGoodsByUserId(String userId) {
		return cartDao.selectByUserId(userId);
	}

	public List<Cart> getGoodsByGoodsIdList(List<String> goodsIdList) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteGoodsFromCart(String userId, String goodsId) {
		return cartDao.deleteByUserIdAndGoodsId(userId, goodsId);
	}

	public boolean updateBuyNumForGoods(String userId, String goodsId,
			int buyNum) {
		return cartDao.updateByUserIdAndGoodsId(userId, goodsId, buyNum);
	}

	public Cart getCartGoodsByUserIdAndGoodsId(String userId, String goodsId) {
		return cartDao.selectByUserIdAndGoodsId(userId,goodsId);
	}

	public boolean addGoodsToCart(String userId, String goodsId, int num) {
		Date time = new Date();
		Cart cart = new Cart(userId, goodsId, num, time);
		return cartDao.insertSelective(cart);
	}

}
