package com.webshop.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webshop.entity.Address;
import com.webshop.entity.Goods;
import com.webshop.entity.Order;
import com.webshop.entity.User;
import com.webshop.service.AddressService;
import com.webshop.service.CartService;
import com.webshop.service.GoodsService;
import com.webshop.service.OrderService;
import com.webshop.service.impl.AddressServiceImpl;
import com.webshop.service.impl.CartServiceImpl;
import com.webshop.service.impl.GoodsServiceImpl;
import com.webshop.service.impl.OrderServiceImpl;

/**
 * @author 黄勇康
 * @date   2017
 */
@WebServlet("/buy.jsp")
public class BuyServlet extends BaseServlet {

	private GoodsService goodsService = new GoodsServiceImpl();
	private AddressService addressService = new AddressServiceImpl();
	private OrderService orderService = new OrderServiceImpl();
	
	//结算
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String[] goodsIdArray = request.getParameterValues("goodsId");
		String[] goodsBuyNum = request.getParameterValues("buyNum");
		String[] isChoose = request.getParameterValues("cart-goods");
		String add = (String)request.getParameter("address");
		//System.out.println(isChoose.length + "aaaaaaaaaaaaaaaa");
		//判断地址是否有
		Address address = addressService.getAddressByUserIdAndAddress(user.getUserId(), add);
		boolean res1 = true;
		if (address == null) {//没有则插入
			address = new Address();
			address.setAddressId(UUID.randomUUID().toString());
			address.setAddress(add);
			address.setUserId(user.getUserId());
			res1 = addressService.insertAddress(address);
		}
		//插入订单，插入订单子表，从购物车中删除
		Order order = new Order(UUID.randomUUID().toString(), user.getUserId(), new Date(), address.getAddressId());
		boolean res2 = orderService.addOrder(order);
		boolean res3 = orderService.addOrderChildTable(user.getUserId(), order.getOrderId(),goodsIdArray, goodsBuyNum, isChoose);
		request.getRequestDispatcher("user.jsp?page=orderPage").forward(request, response);
		//System.out.println("success");
	}
	
	//接收购买信息  检测是否有足够的库存。（时间问题，没有继续写）
	public void confirmNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String[] goodsIdArray = request.getParameterValues("goodsId");
		String[] goodsBuyNum = request.getParameterValues("buyNum");
		System.out.println();
		String result = "success";
		response.setContentType("text/html; charset=UTF-8");
		
		for (int i = 0; i < goodsIdArray.length; i++) {
			String goodsId = goodsIdArray[i];
			Goods goods = goodsService.getGoodsByGoodsId(goodsId);
			if (goods.getGoodsNum() < Integer.parseInt(goodsBuyNum[i])) {
				result = goods.getGoodsName();
				result += "GoodsNum" + goods.getGoodsNum();
				response.getOutputStream().write(result.getBytes("UTF-8"));
				return;
			}
		}
		
		response.getOutputStream().write(result.getBytes("UTF-8"));
	}

}
