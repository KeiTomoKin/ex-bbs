package com.example.Controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Form.ArticleForm;
import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

/**
 * 記事関連機能の処理を行うコントローラ.
 * 
 * @author keita.tomooka
 *
 */
@Controller
@RequestMapping("/bbs")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CommentRepository commentRepository;

	@ModelAttribute
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}

	/**
	 * 記事一覧画面に遷移する.
	 * 
	 * @return 記事一覧画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		for (int i = 0; i < articleList.size(); i++) {
			int articleId = i + 1;
			System.out.println(articleId);
			List<Comment> commentList = commentRepository.findByArticleId(articleId);
			articleList.get(i).setCommentList(commentList);
		}
		model.addAttribute("articleList", articleList);
		return "bbs";
	}

	/**
	 * 記事を追加して、記事一覧画面を更新する。
	 * 
	 * @param form 入力内容
	 * @return 記事一覧画面画
	 */
	@RequestMapping("/insertArticle")
	public String insert(ArticleForm form) {
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);
		return "redirect:/bbs";

	}

}
