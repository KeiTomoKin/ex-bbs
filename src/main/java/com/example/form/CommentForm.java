package com.example.form;

import javax.validation.constraints.NotBlank;

/**
 * コメントのフォームクラス.
 * @author keita.tomooka
 *
 */
public class CommentForm {
	/** 名前 */
	@NotBlank(message="名前を入力してください")
	private String name;
	/** コンテント */
	@NotBlank(message="コメントを入力してください")
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
