package com.example.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
public class ArticleRepository {
//	private RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
//		Article article = new Article();
//		article.setId(rs.getInt("id"));
//		article.setName(rs.getString("name"));
//		article.setContent(rs.getString("content"));
//		return article;
//	};
	
//	private ResultSetExtractor<List<Article>> ARTICLE_RS_EXTRACTOR=(rs) -> {
//		Map<String, Article> map = new LinkedHashMap<String, Article>();
//		Article article = null;
//		
//		while(rs.next()) {
//			String id =rs.getString("id");
//			article = map.get(id);
//			if(article == null) {
//				article = new Article();
//				article.setId(Integer.valueOf(id));
//				article.setName(rs.getString("name"));
//				article.setContent(rs.getString("content"));
//				article.setCommentList(new ArrayList<>());
//				map.put(id, article);
//			}
//			article = map.get(id);
//			String comId = rs.getString("com_id");
//			Comment comment = new Comment();
//			comment.setId(Integer.valueOf(comId));
//			comment.setName(rs.getString("com_name"));
//			comment.setContent(rs.getString("com_content"));
//			comment.setArticleId(rs.getInt("article_id"));
//			article.getCommentList().add(comment);
//			System.out.println(article);
//		}
//		
//		return new ArrayList<Article>(map.values());
//	};
	
	private ResultSetExtractor<List<Article>> ARTICLE_RS_EXTRACTOR=(rs) -> {
		List<Article> articleList= new ArrayList<>();
		ArrayList<Comment> commentList = null;
		int beforeId=0;
		while(rs.next()){
			int id =rs.getInt("id");
			System.out.println(id);
			if(id!=beforeId) {
				Article article = new Article();
				article.setId(Integer.valueOf(id));
				article.setName(rs.getString("name"));
				article.setContent(rs.getString("content"));
				commentList = new ArrayList<>();
				article.setCommentList(commentList);
				articleList.add(article);
			}
			if(rs.getString("com_content")!=null) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("com_id"));
				comment.setName(rs.getString("com_name"));
				comment.setContent(rs.getString("com_content"));
				comment.setArticleId(rs.getInt("article_id"));
				commentList.add(comment);
			}
			beforeId=id;
		}
		return articleList;
	};
	
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * すべての記事情報を取得する.
	 * 
	 * @return 記事情報のリスト
	 * @throws SQLException 
	 */
//	初級用
//	public List<Article> findAll() {
//		String sql = "SELECT id,name,content FROM articles ORDER BY id DESC";
	
//		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
//		return articleList;
//	}
	public List<Article> findAll() {
		String sql =  "SELECT a.id,a.name,a.content,c.id AS com_id,c.name AS com_name,c.content AS com_content,c.article_id FROM articles a LEFT OUTER JOIN comments c ON a.id=c.article_id ORDER BY a.id DESC,c.id DESC";		
		List<Article> articleList = template.query(sql, ARTICLE_RS_EXTRACTOR);
		return articleList;
	}	

	/**
	 * 記事情報を追加する.
	 * 
	 * @param article 追加する記事情報
	 */
	public void insert(Article article) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		String insertSql = "INSERT INTO articles(name,content) VALUES (:name,:content)";
		template.update(insertSql, param);
	}
	
	/**
	 * 記事情報を削除する.
	 * @param id 削除する記事情報
	 */
	public void deleteById(int id) {
		String deleteSql = "WITH deleted AS (DELETE FROM articles WHERE id = :id RETURNING id) DELETE FROM comments WHERE article_id IN (SELECT id FROM deleted);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(deleteSql, param);
	}



}
