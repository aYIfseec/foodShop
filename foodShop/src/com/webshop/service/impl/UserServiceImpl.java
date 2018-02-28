package com.webshop.service.impl;

import com.webshop.dao.UserDao;
import com.webshop.dao.impl.UserDaoImpl;
import com.webshop.entity.User;
import com.webshop.service.UserService;

/**
 * @author 黄勇康
 * @date   2017
 */
public class UserServiceImpl implements UserService{

	private UserDao userDao = new UserDaoImpl();
	
	public User selectUser(User user) {
		User returnUser = null;
		if (user.getPassword() == null) {
			returnUser = userDao.selectUserById(user.getUserId());
		} else {
			returnUser = userDao.selectUserByIdAndPass(user.getUserId(), user.getPassword());
		}
		return returnUser;
	}

	public boolean insertUser(User user) {
		return userDao.insertUser(user);
	}
	
}
