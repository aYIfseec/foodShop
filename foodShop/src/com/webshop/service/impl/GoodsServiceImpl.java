package com.webshop.service.impl;

import java.util.List;

import com.webshop.dao.GoodsDao;
import com.webshop.dao.impl.GoodsDaoImpl;
import com.webshop.entity.Goods;
import com.webshop.entity.GoodsImg;
import com.webshop.service.GoodsService;

/**
 * @author 黄勇康
 * @date   2017
 */
public class GoodsServiceImpl implements GoodsService {

	private GoodsDao goodsDao = new GoodsDaoImpl();
	
	public Goods getGoodsByGoodsId(String goodsId) {
		return goodsDao.selectByPrimaryKey(goodsId);
	}

	public List<GoodsImg> getGoodsImg(String goodsId) {
		return goodsDao.selectGoodsImg(goodsId);
	}

	public List<Goods> getSellingGoods() {
		return goodsDao.selectSellingGoods();
	}

	public List<Goods> searchGoods(String condition) {
		return goodsDao.selectGoodsByCondition(condition);
	}

	public List<Goods> getGoodsByType(String string, int type) {
		if (type == 1) {
			return goodsDao.selectGoodsByClass(string, "limit 6");
		} else {
			if (string.equals("cai")) {
				string = "菜";
			} else if (string.equals("shuiguo")) {
				string = "水果";
			} else {
				string = "甜品";
			}
			return goodsDao.selectGoodsByClass(string, "");
		}
	}

	
}
