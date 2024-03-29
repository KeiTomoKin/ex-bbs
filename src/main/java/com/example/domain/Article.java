package com.example.domain;

import java.util.List;

/**
 * 記事を表すドメイン.
 * 
 * @author keita.tomooka
 *
 */
public class Article {
	/** ID */
	private Integer id;
	/** 投稿者名 */
	private String name;
	/** コンテント */
	private String content;
	/** コメントのリスト */
	private List<Comment> commentList;

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", cmmentList=" + commentList + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

}
