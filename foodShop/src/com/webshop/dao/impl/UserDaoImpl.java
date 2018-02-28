package com.webshop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.webshop.dao.UserDao;
import com.webshop.entity.User;

/**
 * @author 黄勇康
 * @date   2017
 */
public class UserDaoImpl implements UserDao {

	public boolean insertUser(User user) {
		
		String sql2 = "(userId, ";
		String sql3 = "values(?, ";
		
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(user.getUserId());
		if (user.getUserName() != null) {
			sql2 += "userName, ";
			sql3 += "?, ";
			parameters.add(user.getUserName());
		}
		sql2 += "password)";
		sql3 += "?)";
		parameters.add(user.getPassword());
		String sql = "insert into t_user" + sql2 + sql3;
		boolean res = BaseDao.operUpdate(sql, parameters);
		return res;
	}

	public User selectUser(User user) {
		String sql = "select * from t_user where userId=? and password=?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(user.getUserId());
		parameters.add(user.getPassword());
		
		List<User> userList = null;
		try {
			userList = BaseDao.operQuery(sql, parameters, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(userList.size() == 0) return null;
		return userList.get(0);
	}

	public List<User> selectAllUser() {
		String sql = "select * from t_user";
		List<User> userList = null;
		try {
			userList = BaseDao.operQuery(sql, null, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	public boolean deleteUserByUserId(String userId) {
		String sql = "delete from t_user where userId = '" + userId + "'";
		boolean res = BaseDao.operUpdate(sql, null);
		return res;
	}

	public boolean updateUser(User user) {
		String sql = "update t_user set userName=?, password=?, userHeadImg=? where userId = ?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(user.getUserName());
		parameters.add(user.getPassword());
		parameters.add(user.getUserHeadImg());
		parameters.add(user.getUserId());
		boolean res = BaseDao.operUpdate(sql, parameters);
		return res;
	}
	
	public boolean updateUserAgile(User user) {
		String sql = "update t_user set ";
		List<Object> parameters = new ArrayList<Object>();
		if (user.getUserName() != null) {
			sql += "userName=?, ";
			parameters.add(user.getUserName());
		}
		if (user.getPassword() != null) {
			sql += "password=?, ";
			parameters.add(user.getPassword());
		}
		if (user.getUserHeadImg() != null) {
			sql += "userHeadImg=? ";
			parameters.add(user.getUserHeadImg());
		}
		parameters.add(user.getUserId());
		sql += "where userId = ?";
		boolean res = BaseDao.operUpdate(sql, parameters);
		return res;
	}

	public User selectUserById(String userId) {
		String sql = "select * from t_user where userId=?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(userId);
		
		List<User> userList = null;
		try {
			userList = BaseDao.operQuery(sql, parameters, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(userList.size() == 0) return null;
		return userList.get(0);
	}

	public User selectUserByIdAndPass(String userId, String password) {
		String sql = "select * from t_user where userId=? and password=?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(userId);
		parameters.add(password);
		
		List<User> userList = null;
		try {
			userList = BaseDao.operQuery(sql, parameters, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(userList.size() == 0) return null;
		return userList.get(0);
	}

}
