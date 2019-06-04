package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * commentsテーブルを操作するリポジトリ.
 * 
 * @author keita.tomooka
 *
 */
@Repository
public class CommentRepository {
	private RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		return comment;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 記事に紐づいたコメント情報のリストを取得する.
	 * 
	 * @param articleId 記事ID
	 * @return コメント情報のリスト
	 */
	public List<Comment> findByArticleId(int articleId) {
		String sql = "SELECT id,name,content,article_id FROM comments WHERE article_id=:articleId ORDER BY id DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
		return commentList;
	}

	/**
	 * コメント情報を追加する.
	 * 
	 * @param comment 追加するコメント情報
	 */
	public void insert(Comment comment) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		String insertSql = "INSERT INTO comments(name,content,article_id) VALUES (:name,:content,:articleId)";
		template.update(insertSql, param);
	}

	/**
	 * 記事情報を削除する.
	 * 
	 * @param id 削除する記事情報
	 */
	public void deleteByArticleId(int articleId) {
		String deleteSql = "DELETE FROM comments WHERE article_id=:articleId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(deleteSql, param);
	}
}
