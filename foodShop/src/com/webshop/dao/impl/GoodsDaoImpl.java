package com.webshop.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.webshop.dao.GoodsDao;
import com.webshop.entity.Goods;
import com.webshop.entity.GoodsImg;

/**
 * @author 黄勇康
 * @date   2017
 */
public class GoodsDaoImpl implements GoodsDao {

	public int deleteByPrimaryKey(String goodsid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Goods record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(Goods record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Goods selectByPrimaryKey(String goodsId) {
		String sql = "select * from t_goods where goodsId = ?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(goodsId);
		List<Goods> goodsList = null;
		try {
			goodsList = BaseDao.operQuery(sql, parameters, Goods.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsList.size() == 0 ? null : goodsList.get(0);
	}

	public int updateByPrimaryKeySelective(Goods record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Goods record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Goods> selectAllGoods() {
		//测试用
		String sql = "select * from t_goods";
		List<Goods> goodsList = null;
		try {
			goodsList = BaseDao.operQuery(sql, null, Goods.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsList;
	}

	public List<GoodsImg> selectGoodsImg(String goodsId) {
		String sql = "select * from t_goods_img where goodsId = ?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(goodsId);
		List<GoodsImg> goodsImgList = null;
		try {
			goodsImgList = BaseDao.operQuery(sql, parameters, GoodsImg.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsImgList;
	}

	//销量前6
	public List<Goods> selectSellingGoods() {
		String sql = "select * from t_goods order by salesNum desc limit 6";
		List<Goods> goodsList = null;
		try {
			goodsList = BaseDao.operQuery(sql, null, Goods.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsList;
	}

	public List<Goods> selectGoodsByClass(String type, String sql2) {
		String sql = "select * from t_goods where goodsClass like ? order by salesNum desc " + sql2;
		List<Goods> goodsList = null;
		type = "%" + type + "%";
		List<Object> paramenter = new ArrayList<Object>();
		paramenter.add(type);
		try {
			goodsList = BaseDao.operQuery(sql, paramenter, Goods.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsList;
	}
	
	public List<Goods> selectGoodsByCondition(String condition) {
		String sql = "select * from t_goods where goodsName like ? or goodsClass like ? order by salesNum desc";
		List<Goods> goodsList = null;
		condition = "%" + condition + "%";
		List<Object> paramenter = new ArrayList<Object>();
		paramenter.add(condition);
		paramenter.add(condition);
		try {
			goodsList = BaseDao.operQuery(sql, paramenter, Goods.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsList;
	}

	public boolean updateByPrimaryKey(String string2, int num, int num2) {
		String sql = "update t_goods set goodsNum=?, salesNum=? where goodsId = ?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(num);
		parameters.add(num2);
		parameters.add(string2);
		boolean res = BaseDao.operUpdate(sql, parameters);
		return res;
	}

	public Goods selectByGoodsId(String string) {
		String sql = "select goodsNum, salesNum from t_goods where goodsId = ?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(string);
		List<Goods> goodsList = null;
		try {
			goodsList = BaseDao.operQuery(sql, parameters, Goods.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsList.size() == 0 ? null : goodsList.get(0);
	}

}
