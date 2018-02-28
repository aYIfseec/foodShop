package com.webshop.dao;

import java.util.List;

import com.webshop.entity.Comment;

public interface CommentDao {
    int deleteByPrimaryKey(Integer commentId);

    boolean insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

	/**
	 * @param goodsId
	 * @return
	 */
	List<Comment> selectByGoodsId(String goodsId);

	/**
	 * @param goodsId
	 * @param userId
	 * @return
	 */
	Comment selectByGoodsIdAndUserId(String goodsId, String userId);
}