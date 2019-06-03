package com.example.Form;

/**
 * 記事のフォームクラス.
 * 
 * @author keita.tomooka
 *
 */
public class ArticleForm {
	private String name;
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
