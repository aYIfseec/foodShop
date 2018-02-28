package com.webshop.service;

import com.webshop.entity.User;

/**
 * @author 黄勇康
 * @date   2017
 */
public interface UserService {

	/**
	 * @param user
	 * @return
	 */
	User selectUser(User user);

	/**
	 * @param user
	 * @return
	 */
	boolean insertUser(User user);
	
}
