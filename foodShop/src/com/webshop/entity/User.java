package com.webshop.entity;

/**
 * @author 黄勇康
 * @date   2017
 */
public class User {
	private String userId;
	private String userName;
	private String password;
	private String userHeadImg;
	
	public User() {}
	
	/**
	 * @param userId2
	 * @param password2
	 */
	public User(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
	/**
	 * @param userId2
	 */
	public User(String userId) {
		this.userId = userId;
	}

	/**
	 * @param userId2
	 * @param userName2
	 * @param password2
	 */
	public User(String userId, String userName, String password) {
		this.userId = userId;
		this.password = password;
		this.userName = userName;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the userHeadImg
	 */
	public String getUserHeadImg() {
		return userHeadImg;
	}
	/**
	 * @param userHeadImg the userHeadImg to set
	 */
	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", userHeadImg=" + userHeadImg
				+ "]";
	}
}
