package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * articlesテーブルを操作するリポジトリ.
 * 
 * @author keita.tomooka
 *
 */
@Repository
public class ArticleRepository extends Article {
	private RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		return article;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private CommnetRepository commnetRepository;

	
	

	/**
	 * すべての記事情報を取得する.
	 * 
	 * @return 記事情報のリスト
	 */
	public List<Article> findAll() {
		String sql = "SELECT id,name,content FROM articles ORDER BY id DESC";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		for(int i= 0;i<articleList.size();i++) {
			int articleId=i+1;
			System.out.println(articleId);
			List<Comment> commentList = commnetRepository.findByArticleId(articleId);
			articleList.get(i).setCommentList(commentList);
		}
		return articleList;
	}
	


	/**
	 * 記事情報を追加する.
	 * @param article 追加する記事情報
	 */
	public void insert(Article article) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		String insertSql = "INSERT INTO articles(name,content) VALUES (:name,:content)";
		template.update(insertSql, param);
	}

}
