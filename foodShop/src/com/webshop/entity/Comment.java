package com.webshop.entity;

import java.util.Date;

public class Comment {
    private Integer commentId;

    private String goodsId;

    private String userId;

    private String comment;

    private int score;

    private Date time;

    
    public Comment(){}
    /**
	 * @param goodsId2
	 * @param userId2
	 * @param comment2
	 * @param score2
	 * @param date
	 */
	public Comment(String goodsId2, String userId2, String comment2,
			int score2, Date date) {
		this.goodsId = goodsId2;
		this.userId = userId2;
		this.comment = comment2;
		this.score = score2;
		this.time = date;
	}

	public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}