package test2.container;

import java.util.Scanner;

import test2.controller.ArticleController;
import test2.controller.MemberController;
import test2.dao.ArticleDao;
import test2.dao.MemberDao;
import test2.service.ArticleService;
import test2.service.MemberService;
import test2.session.Session;

public class Container {

	public static MemberController memberController;
	public static ArticleController articleController;
	public static Scanner scanner;
	public static MemberService memberService;
	public static MemberDao memberDao;
	public static Session session;
	public static ArticleService articleService;
	public static ArticleDao articleDao;

	static {
		scanner = new Scanner(System.in);
		session = new Session();
		
		memberDao = new MemberDao();
		articleDao = new ArticleDao(); 
		memberService = new MemberService(); 
		articleService = new ArticleService(); 
		memberController = new MemberController(); 
		articleController = new ArticleController(); 
	}
}
