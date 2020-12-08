package test2.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import test2.controller.Board;
import test2.dto.Article;
import test2.dto.Member;
import test2.mysqlUtil.MysqlUtil;
import test2.mysqlUtil.SecSql;

public class ArticleDao {

	private List<Article> article;
	private int lastArticleId;

	public ArticleDao() {
		article = new ArrayList<>();
		lastArticleId = 0;
	}

	public Board getBoardByName(String boardName) {
		SecSql sql = new SecSql();
		sql.append("select * from board where boardName = ?", boardName);

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}

		return new Board(map);
	}

	public int makeBoard(String boardName) {

		SecSql sql = new SecSql();
		sql.append("INSERT INTO board set boardName = ?", boardName);

		return MysqlUtil.insert(sql);

	}

	public Board getBoardById(int id) {

		SecSql sql = new SecSql();
		sql.append("select * from board where id = ?", id);

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}

		return new Board(map);

	}

	public int add(int boardId, String title, String body, int memberId) {

		SecSql sql = new SecSql();
		sql.append("INSERT INTO article set boardId = ?, title = ?, body = ?, memberId = ?, "
				+ "regDate = now(), updateDate = now(), hit = 0", boardId, title, body, memberId);

		return MysqlUtil.insert(sql);

	}

	public List<Article> getArticles(int inputedId) {

		List<Article> newArticles = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("select article.*, member.name as extra_writer from article " + "inner join member on boardId = ?",
				inputedId);

		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articleMap : articleMapList) {
			newArticles.add(new Article(articleMap));
		}

		return newArticles;
	}

	public Article getArticle(int inputedId) {

		SecSql sql = new SecSql();
		sql.append("select article.*, member.name as extra_writer from article inner join member on id = ?", inputedId);
		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}

		return new Article(map);
	}

	public void hitCount(int boardId, int inputedId) {
		SecSql sql = new SecSql();

		sql.append("update article set hit = hit+1 where boardId = ? and id = ?", boardId, inputedId);
		MysqlUtil.update(sql);
	}

	public void modify(int inputedId, String title, String body) {
		SecSql sql = new SecSql();

		sql.append("update article set title = ?, body = ?, updateDate = now() where articleId = ?", title, body, inputedId );
		MysqlUtil.update(sql);
	}

	public int delete(int inputedId) {
		
		SecSql sql = new SecSql();
		sql.append("delete from article where id = ?", inputedId); 
		
		return MysqlUtil.delete(sql);
	}

	

}
