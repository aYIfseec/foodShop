package com.webshop.dao;

import java.util.List;

import com.webshop.entity.Goods;
import com.webshop.entity.GoodsImg;


public interface GoodsDao {
    int deleteByPrimaryKey(String goodsid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String goodsid);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
    
    //List<Goods> selectByGoodsClass();
    List<Goods> selectAllGoods();

	/**
	 * @param goodsId
	 * @return
	 */
	List<GoodsImg> selectGoodsImg(String goodsId);

	/**
	 * @return
	 */
	List<Goods> selectSellingGoods();

	/**
	 * @return
	 */
	List<Goods> selectGoodsByCondition(String condition);
	
	List<Goods> selectGoodsByClass(String type, String sql);

	/**
	 * @param string
	 * @param string2
	 */
	boolean updateByPrimaryKey( String string2, int num, int num2);

	/**
	 * @param string
	 * @return
	 */
	Goods selectByGoodsId(String string);
    
}