package com.webshop.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webshop.dao.UserDao;
import com.webshop.dao.impl.UserDaoImpl;
import com.webshop.entity.Address;
import com.webshop.entity.Comment;
import com.webshop.entity.Order;
import com.webshop.entity.User;
import com.webshop.service.AddressService;
import com.webshop.service.CommentService;
import com.webshop.service.OrderService;
import com.webshop.service.impl.AddressServiceImpl;
import com.webshop.service.impl.CommentServiceImpl;
import com.webshop.service.impl.OrderServiceImpl;

/**
 * @author 黄勇康
 * @date 2017
 */
@WebServlet("/user.jsp")
public class UserServlet extends BaseServlet {

	private AddressService addressService = new AddressServiceImpl();
	private OrderService orderService = new OrderServiceImpl();
	private UserDao userDao = new UserDaoImpl();
	private CommentService commentService = new CommentServiceImpl();

	// 跳至用户信息页面
	public void userPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/userInfo.jsp").forward(
				request, response);
	}

	// 读取用户订单信息，跳至订单页面
	public void orderPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Order> orderList = orderService.getOrderByUserId(user.getUserId());
		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("/WEB-INF/user/myOrder.jsp").forward(
				request, response);
	}

	// 读取用户地址信息，跳至地址页面
	public void addressPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Address> addressList = null;
		addressList = addressService.getAddressByUserId(user.getUserId());
		request.setAttribute("addressList", addressList);
		request.getRequestDispatcher("/WEB-INF/user/myAddress.jsp").forward(
				request, response);
	}

	//跳至评论窗口
	public void commentPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String goodsId = (String)request.getParameter("goodsId");
		//System.out.println(user.toString() + goodsId);
		
		//查是否已评论
		Comment comment = commentService.getCommentByGoodsIdAndUserId(goodsId,user.getUserId());
		request.setAttribute("comment", comment);
		request.setAttribute("goodsId", goodsId);
		request.getRequestDispatcher("/WEB-INF/user/comment.jsp").forward(
				request, response);
	}
	
	//添加评论
	public void userComment(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String goodsId = (String)request.getParameter("goodsId");
		String comment = (String)request.getParameter("comment");
		String score = (String)request.getParameter("score");
		int s = 5;
		String str = "fail";
		try {
			s = Integer.parseInt(score);
		} catch (Exception e) {
			response.getOutputStream().write(str.getBytes("UTF-8"));
		}
		//防止改前端代码评负分等
		if (s > 5) {//超过5，按5分
			s = 5;
		} else if (s < 1) {//小于1按一分
			s = 1;
		}
		//System.out.println(goodsId);
		response.setCharacterEncoding("utf-8");
		Comment commentObj = new Comment(goodsId, user.getUserId(), comment, s, new Date());
		boolean res = commentService.addComment(commentObj);
		if (res) {
			str = "success";
		}
		
		response.getOutputStream().write(str.getBytes("UTF-8"));
	}

	//增加地址
	public void addAddress(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String add = (String)request.getParameter("address");
		//判断地址是否有
		Address address = addressService.getAddressByUserIdAndAddress(user.getUserId(),add);
		boolean res1 = false;
		String str = null;
		if (address == null) {//没有则插入
			address = new Address();
			address.setAddressId(UUID.randomUUID().toString());
			address.setAddress(add);
			address.setUserId(user.getUserId());
			res1 = addressService.insertAddress(address);
		} else {
			str = "exit";
		}
		if (res1) {
			str = "success";
		}
		response.getOutputStream().write(str.getBytes("UTF-8"));
	}
	
	// 修改用户信息
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("hello");
		HttpSession session = request.getSession();
		User userInfo = (User) session.getAttribute("user");
		String str = null;
		response.setCharacterEncoding("utf-8");
		String oldPass = (String) request.getParameter("oldPass");
		if (oldPass == null || !userInfo.getPassword().equals(oldPass)) {
			str = "wrong";
			response.getWriter().print(str);
			return;
		}
		User user = new User();
		user.setUserId((String) request.getParameter("userId"));
		user.setUserName((String) request.getParameter("userName"));
		user.setPassword((String) request.getParameter("password"));
		user.setUserHeadImg(userInfo.getUserHeadImg());
		boolean res = userDao.updateUser(user);
		str = "fail";
		if (res) {
			str = "success";
		}
		response.getWriter().print(str);
	}

}
