package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

@Repository
public class ArticleRepository extends Article {
	private RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
//		article.setCommentList(rs.get("commentList"));
		return article;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
//	
//	public List<Comment> findByArticleId(int articleId){
//		String sql = "SELECT id,name,content FROM articles WHERE id <= :Article ORDER BY id DESC";
//		SqlParameterSource param = new MapSqlParameterSource().addValue("id", articleId);
//		List<Article> articleList = template.query(sql, param, ARTICLE_ROW_MAPPER);
//		return articleList;	
//	}
	
	public List<Article> findAll(){
		String sql = "SELECT id,name,content FROM articles ORDER BY id DESC";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		return articleList;
	}
	
	
}
