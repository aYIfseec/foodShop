package com.webshop.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webshop.entity.GoodsImg;
import com.webshop.entity.Comment;
import com.webshop.entity.Goods;
import com.webshop.service.CommentService;
import com.webshop.service.GoodsService;
import com.webshop.service.impl.CommentServiceImpl;
import com.webshop.service.impl.GoodsServiceImpl;


/**
 * @author 黄勇康
 * @date   2017
 */
@WebServlet("/home.jsp")
public class ShowServlet extends BaseServlet{
	
	private GoodsService goodsService = new GoodsServiceImpl();
	private CommentService commentService = new CommentServiceImpl();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
	}
	
	public void mainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//每种类型按销量取出6件
		List<Goods> vegetable = goodsService.getGoodsByType("菜", 1);
		List<Goods> fruits = goodsService.getGoodsByType("水果", 1);
		List<Goods> dessert = goodsService.getGoodsByType("甜品", 1);
		List<Goods> sellingGoodsList = goodsService.getSellingGoods();
		request.setAttribute("sellingGoodsList",sellingGoodsList);
		request.setAttribute("fruits",fruits);
		request.setAttribute("vegetable",vegetable);
		request.setAttribute("dessert",dessert);
		request.getRequestDispatcher("/WEB-INF/pages/main.jsp").forward(request, response);
	}
	
	public void goodsInfoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查其详情，查其评论
		String goodsId = request.getParameter("goodsId");
		Goods goods = goodsService.getGoodsByGoodsId(goodsId);
		List<Comment> commentList = commentService.getCommentByGoodsId(goodsId);
		List<GoodsImg> goodsImgList = goodsService.getGoodsImg(goodsId);
		request.setAttribute("commentList",commentList);
		request.setAttribute("goodsImgList",goodsImgList);
		request.setAttribute("goods",goods);
		request.getRequestDispatcher("/WEB-INF/pages/goodsInfo.jsp").forward(request, response);
	}
	//searchGoods
	public void searchGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String condition = request.getParameter("condition");
		System.out.println(condition);
		List<Goods> goodsList = goodsService.searchGoods(condition);
		//
		//
		request.getSession().setAttribute("goodsListBySearch",goodsList);
		//特殊需要，暂时存在session，前台ajax收到消息后，转到result处理掉
		response.setContentType("text/html; charset=UTF-8");
		String stringer = null;
		if (goodsList.size() == 0) {
			stringer = "fail";
		} else {
			stringer = "success";
		}
		response.getOutputStream().write(stringer.getBytes("UTF-8"));
	}
	
	public void showGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		List<Goods> goodsList = goodsService.getGoodsByType(type, 0);
		request.setAttribute("goodsListBySearch",goodsList);
		if (type.equals("cai")) {
			type = "一些小菜";
		} else if (type.equals("shuiguo")) {
			type = "新鲜的水果";
		} else {
			type = "好吃的甜品";
		}
		request.setAttribute("type", type);
		request.getRequestDispatcher("/WEB-INF/pages/goodsList.jsp").forward(request, response);
	}
	
	public void result(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Goods> goodsList = (List<Goods>)request.getSession().getAttribute("goodsListBySearch");
		request.getSession().removeAttribute("goodsListBySearch");
		request.setAttribute("goodsListBySearch", goodsList);
		request.getRequestDispatcher("/WEB-INF/pages/goodsList.jsp").forward(request, response);
	}
	/*public void testPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> userList = userDao.selectAllUser();
		String message = (String)request.getSession().getAttribute("message");
		if(message != null) {
			request.getSession().removeAttribute(message);
		}
		request.setAttribute("userList",userList);
		request.getRequestDispatcher("test.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println((String)request.getParameter("password"));
		User user = new User();
		user.setUserId((String)request.getParameter("userId"));
		user.setUserName((String)request.getParameter("userName"));
		user.setPassword((String)request.getParameter("password"));
		user.setUserHeadImg((String)request.getParameter("userHeadImg"));
		boolean res = userDao.updateUser(user);
		String message = "修改失败";
		if (res) {
			message = "修改成功";
		}
		//response.setCharacterEncoding("utf-8");
		//response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(message);
		response.setHeader("refresh", "1;URL=home.jsp?page=testPage");
	}
	
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		boolean res = userDao.deleteUserByUserId(userId);
		String message = "删除失败";
		if (res) {
			message = "删除成功";
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(message);
		response.setHeader("refresh", "1;URL=home.jsp?page=testPage");
	}*/
}
