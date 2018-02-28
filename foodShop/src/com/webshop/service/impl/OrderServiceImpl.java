package com.webshop.service.impl;

import java.util.List;

import com.webshop.dao.CartDao;
import com.webshop.dao.GoodsDao;
import com.webshop.dao.OrderChildTableDao;
import com.webshop.dao.OrderDao;
import com.webshop.dao.impl.GoodsDaoImpl;
import com.webshop.dao.impl.OrderChildTableDaoImpl;
import com.webshop.dao.impl.OrderDaoImpl;
import com.webshop.entity.Goods;
import com.webshop.entity.Order;
import com.webshop.service.OrderService;

/**
 * @author 黄勇康
 * @date   2017
 */
public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao = new OrderDaoImpl();
	private OrderChildTableDao orderChildTableDao = new OrderChildTableDaoImpl();
	private GoodsDao goodsDao = new GoodsDaoImpl();
	private CartDao cartDao = new CartDaoImpl();
	
	public boolean addOrder(Order order) {
		return orderDao.insert(order);
	}

	public boolean addOrderChildTable(String userId, String orderId, String[] goodsIdArray,
			String[] goodsBuyNum, String[] isChoose) {
		String val = "";//生成批量插入订单子表语句
		for (int i = 0, j = 0; i < isChoose.length && j < goodsIdArray.length; j++) {
			//System.out.println("####" + isChoose[i]);
			if (!goodsIdArray[j].equals(isChoose[i])) {//跳过购物车中未选中的
				//System.out.println("未");
				continue;
			}
			val += "('"+ orderId +"', '" + goodsIdArray[j] + "', " + goodsBuyNum[j] + ")";
			if (i < isChoose.length - 1) val += ", ";
			
			//从购物车中删除 购买了的商品
			boolean res = cartDao.deleteByUserIdAndGoodsId(userId,  goodsIdArray[j]);
			if (!res) {
				return false;
			}
			
			//查goods数量，库存数量-购买数量.
			Goods goods = goodsDao.selectByGoodsId(goodsIdArray[j]);
			//去修改goods的库存数量， 销售数量
			int goodsNum = goods.getGoodsNum()-Integer.parseInt(goodsBuyNum[j]);
			int salesNum = goods.getSalesNum()+Integer.parseInt(goodsBuyNum[j]);
			if (!goodsDao.updateByPrimaryKey(goodsIdArray[j],goodsNum, salesNum)) {
				return false;
			}
			i++;
		}
		return orderChildTableDao.insert(val);
	}

	public List<Order> getOrderByUserId(String userId) {
		
		return orderDao.selectByUserId(userId);
	}

}
