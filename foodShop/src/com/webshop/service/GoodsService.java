package com.webshop.service;

import java.util.List;

import com.webshop.entity.Goods;
import com.webshop.entity.GoodsImg;

/**
 * @author 黄勇康
 * @date   2017
 */
public interface GoodsService {

	Goods getGoodsByGoodsId(String goodsId);

	List<GoodsImg> getGoodsImg(String goodsId);

	List<Goods> getSellingGoods();

	/**
	 * @param condition
	 * @return
	 */
	List<Goods> searchGoods(String condition);

	/**
	 * @param string
	 * @return
	 */
	List<Goods> getGoodsByType(String string, int type);
	
}
