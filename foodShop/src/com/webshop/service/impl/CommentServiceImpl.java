package com.webshop.service.impl;

import java.util.List;

import com.webshop.dao.CommentDao;
import com.webshop.dao.impl.CommentDaoImpl;
import com.webshop.entity.Comment;
import com.webshop.service.CommentService;

/**
 * @author 黄勇康
 * @date   2017
 */
public class CommentServiceImpl implements CommentService {

	private CommentDao commentDao = new CommentDaoImpl();
	
	public List<Comment> getCommentByGoodsId(String goodsId) {
		return commentDao.selectByGoodsId(goodsId);
	}

	public Comment getCommentByGoodsIdAndUserId(String goodsId, String userId) {
		return commentDao.selectByGoodsIdAndUserId(goodsId,userId);
	}

	public boolean addComment(Comment commentObj) {
		return commentDao.insert(commentObj);
	}

}
