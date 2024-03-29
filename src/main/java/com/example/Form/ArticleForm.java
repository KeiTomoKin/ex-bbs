package com.example.form;

import javax.validation.constraints.NotBlank;

/**
 * 記事のフォームクラス.
 * 
 * @author keita.tomooka
 *
 */
public class ArticleForm {
	/** 投稿者名 */
	@NotBlank(message="投稿者名を入力してください")
	private String name;
	/** 投稿内容 */
	@NotBlank(message="投稿内容を入力してください")
	private String content;

	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + "]";
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

}
