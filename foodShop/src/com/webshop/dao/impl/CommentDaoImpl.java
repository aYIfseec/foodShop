package com.webshop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.webshop.dao.CommentDao;
import com.webshop.entity.Comment;

/**
 * @author 黄勇康
 * @date   2017
 */
public class CommentDaoImpl implements CommentDao {

	public int deleteByPrimaryKey(Integer commentId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean insert(Comment record) {
		String sql = "insert into t_comment(goodsId, userId, comment, score, time) values(?, ?, ?, ?, ?)";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(record.getGoodsId());
		parameters.add(record.getUserId());
		parameters.add(record.getComment());
		parameters.add(record.getScore());
		parameters.add(record.getTime());
		return BaseDao.operUpdate(sql, parameters);
	}

	public int insertSelective(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Comment selectByPrimaryKey(Integer commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Comment> selectByGoodsId(String goodsId) {
		String sql = "select * from t_comment where goodsId = ?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(goodsId);
		List<Comment> commentList = null;
		try {
			commentList = BaseDao.operQuery(sql, parameters, com.webshop.entity.Comment.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentList;
	}

	public Comment selectByGoodsIdAndUserId(String goodsId, String userId) {
		String sql = "select * from t_comment where goodsId = ? and userId = ?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(goodsId);
		parameters.add(userId);
		List<Comment> commentList = null;
		try {
			commentList = BaseDao.operQuery(sql, parameters, Comment.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentList.size() == 0 ? null : commentList.get(0);
	}

}
