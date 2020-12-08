package test2.service;

import java.util.List;

import test2.container.Container;
import test2.controller.Board;
import test2.dao.ArticleDao;
import test2.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public Board getBoardByName(String boardName) {
		return articleDao.getBoardByName(boardName);
	}

	public int makeBoard(String boardName) {
		return articleDao.makeBoard(boardName);
	}

	public Board getBoardById(int id) {
		return articleDao.getBoardById(id);
	}

	public int add(int boardId, String title, String body, int memberId) {
		return articleDao.add(boardId, title, body, memberId);
	}

	public List<Article> getArticles(int inputedId) {
		return articleDao.getArticles(inputedId);
	}

	public Article getArticle(int inputedId) {
		return articleDao.getArticle(inputedId);
	}

	public void hitCount(int boardId, int inputedId) {
		articleDao.hitCount(boardId, inputedId);
	}

	public void modify(int inputedId, String title, String body) {
		articleDao.modify(inputedId, title, body);
	}

	public int delete(int inputedId) {
		return articleDao.delete(inputedId);
	}

	


	


}
