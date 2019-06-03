package com.example.form;

/**
 * コメントのフォームクラス.
 * @author keita.tomooka
 *
 */
public class CommentForm {
	/** 名前 */
	private String name;
	/** コンテント */
	private String content;
	/** 投稿ID */
	private String articleId;
	@Override
	public String toString() {
		return "CommentForm [name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
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
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
}
