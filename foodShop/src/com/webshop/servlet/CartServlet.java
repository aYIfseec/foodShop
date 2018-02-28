package com.webshop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webshop.entity.Address;
import com.webshop.entity.Cart;
import com.webshop.entity.User;
import com.webshop.service.AddressService;
import com.webshop.service.CartService;
import com.webshop.service.impl.AddressServiceImpl;
import com.webshop.service.impl.CartServiceImpl;

/**
 * @author 黄勇康
 * @date 2017
 */
@WebServlet("/cart.jsp")
public class CartServlet extends BaseServlet {

	private CartService cartService = new CartServiceImpl();
	private AddressService addressService = new AddressServiceImpl();
	
	// 转至cart界面
	public void cartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查出该用户购物车中的东西
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Cart> goodsList = null;
		List<Address> addressList = null;
		if (user == null) {//不必了
			//查其cookie获得商品编号
			//goodsList = cartService.getGoodsByGoodsIdList(goodsIdList);
		} else {
			goodsList = cartService.getGoodsByUserId(user.getUserId());
			addressList = addressService.getAddressByUserId(user.getUserId());
		}
		request.setAttribute("goodsList", goodsList);
		request.setAttribute("addressList", addressList);
		request.getRequestDispatcher("/WEB-INF/user/cart.jsp").forward(request, response);
	}
	
	//更新crat
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = ((User)request.getSession().getAttribute("user")).getUserId();
		String goodsId = request.getParameter("goodsId");
		String buyNum = request.getParameter("buyNum");
		String oper = request.getParameter("oper");//判断是修改数量还是插入
		boolean res = false;
		String returnStr = null;
		
		if (buyNum == null) {//则从该用户的cart删除该商品
			res = cartService.deleteGoodsFromCart(userId, goodsId);
		} else {//则为添加或修改数量
			int num = Integer.parseInt(buyNum);
			Cart cart = cartService.getCartGoodsByUserIdAndGoodsId(userId, goodsId);
			if (cart == null) {//不存在则添加
				res = cartService.addGoodsToCart(userId, goodsId, num);
			} else {//存在则从该用户的cart修改该商品buyNum
				if (oper == null) {//在购物车中修改数量
					res = cartService.updateBuyNumForGoods(userId, goodsId, num);
				} else {//添加时的数量+原先该商品在购物车中的数量
					res = cartService.updateBuyNumForGoods(userId, goodsId, cart.getBuyNum()+num);
				}
			}
		}
		if (res) {
			returnStr = "success";
		} else {
			returnStr = "fail";
		}
		response.setContentType("text/html; charset=UTF-8");
		response.getOutputStream().write(returnStr.getBytes("UTF-8"));
		return;
	}

}
