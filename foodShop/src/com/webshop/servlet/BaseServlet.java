package com.webshop.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author 榛勫媷搴�
 * 
 * @date 2017
 * 
 * @鍔熻兘 瀹炵幇涓�釜Servlet绫婚噷澶勭悊澶氱璇锋眰
 */
public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)

	throws ServletException, IOException {
		
		String name = req.getParameter("page");// 鑾峰彇鏂规硶鍚�
		
		Class c = this.getClass();// 鑾峰緱褰撳墠绫荤殑Class瀵硅薄
		Method method = null;
		if (name == null || name.isEmpty()) {
			//System.out.println("娌℃湁浼犻�page鍙傛暟锛岃皟鐢ㄩ粯璁ょ殑doPost");
			try {
				method = c.getMethod("doPost", HttpServletRequest.class,
						HttpServletResponse.class);
				method.invoke(this, req, resp);// 鍙嶅皠璋冪敤鏂规硶
				return;
			} catch (SecurityException e) {
				throw new RuntimeException("娌℃湁鎵惧埌doPost");
			}  catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		try {
			// 鑾峰緱Method瀵硅薄
			method = c.getMethod(name, HttpServletRequest.class,
					HttpServletResponse.class);
		} catch (Exception e) {
			req.getRequestDispatcher("/404.jsp").forward(req, resp);
		}

		try {
			method.invoke(this, req, resp);// 鍙嶅皠璋冪敤鏂规硶
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
