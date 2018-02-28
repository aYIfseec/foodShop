package com.webshop.service;

import java.util.List;

import com.webshop.entity.Comment;

/**
 * @author 黄勇康
 * @date   2017
 */
public interface CommentService {

	/**
	 * @return
	 */
	List<Comment> getCommentByGoodsId(String goodsId);

	/**
	 * @param goodsId
	 * @param userId
	 * @return
	 */
	Comment getCommentByGoodsIdAndUserId(String goodsId, String userId);

	/**
	 * @param commentObj
	 * @return
	 */
	boolean addComment(Comment commentObj);
	
}
