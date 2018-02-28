package com.webshop.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.webshop.entity.User;
import com.webshop.service.UserService;
import com.webshop.service.impl.UserServiceImpl;


/**
 * @author 黄勇康
 * @date   2017
 */
@WebServlet("/login.jsp")
public class LoginServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	
	UserService userService = new UserServiceImpl();
	
	//转至登录界面
	public void loginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}
	
	//登录验证
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//获得登录时输入的信息
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		//System.out.println(userId+password);
		//查该用户
		User user = userService.selectUser(new User(userId, password));
		//JSONStringer stringer = new JSONStringer();
		String stringer = null;
		//用户验证
		if(user != null && user.getPassword().equals(password)) {
			//stringer.object().key("state").value("success").endObject();
			stringer = "success";
			HttpSession session = request.getSession();
			session.setAttribute("user", user);//将用户信息存入session
		} else {
			stringer = "fail";
			//stringer.object().key("state").value("fail").endObject();
		}
		//System.out.println(stringer);
		response.setContentType("text/html; charset=UTF-8");
		response.getOutputStream().write(stringer.getBytes("UTF-8"));
		//response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
	}
	
	//用户退出
	public void userLogout(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();//销毁session
		response.sendRedirect("home.jsp");
	}
	
}
