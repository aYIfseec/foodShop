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
import com.webshop.servlet.BaseServlet;

/**
 * @author 黄勇康
 * @date 2017
 */
@WebServlet("/register.jsp")
public class RegisterServlet extends BaseServlet {

	UserService userService = new UserServiceImpl();

	public void registerPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(
				request, response);
	}

	// 注册
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得注册时输入的信息
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		System.out.println(userId + password);
		// 查该用户是否已注册
		User user = userService.selectUser(new User(userId));
		String stringer = null;
		if (user == null) {
			boolean res = userService.insertUser(new User(userId, userName,
					password));
			if (res) {
				stringer = "success";
			} else {
				stringer = "fail";
			}
		} else {
			stringer = "exist";
		}
		//System.out.println(stringer);
		response.setContentType("text/html; charset=UTF-8");
		response.getOutputStream().write(stringer.getBytes("UTF-8"));
	}
}
