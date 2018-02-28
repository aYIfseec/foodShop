package com.webshop.dao;

import java.util.List;

import com.webshop.entity.User;


/**
 * @author 黄勇康
 * @date   2017
 */
public interface UserDao {
	
	public boolean insertUser(User user);
	
	public boolean updateUser(User user);
	
	public boolean updateUserAgile(User user);

	public User selectUser(User user);

	public List<User> selectAllUser();

	public boolean deleteUserByUserId(String userId);

	/**
	 * @param userId
	 * @return
	 */
	public User selectUserById(String userId);

	/**
	 * @param userId
	 * @param password
	 * @return
	 */
	public User selectUserByIdAndPass(String userId, String password);
	
}
