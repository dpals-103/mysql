package test2;

import java.util.Scanner;

import test2.container.Container;
import test2.controller.ArticleController;
import test2.controller.Controller;
import test2.controller.MemberController;
import test2.mysqlUtil.MysqlUtil;

public class App {

	private MemberController memberController;
	private ArticleController articleController;
	
	public App() {
		memberController = Container.memberController;
		articleController = Container.articleController;
	}
	public void run() {
		
		Scanner sc = Container.scanner; 
		
		while(true) {
			
			System.out.printf("명령어) ");
			String cmd = sc.nextLine(); 
			
			MysqlUtil.setDBInfo("localhost", "dpals103", "dlgywn0168", "a2");
			
			boolean needToExit = false; 
			
			if(cmd.equals("exit")) {
				System.out.println("프로그램종료");
				needToExit = true; 
			}
			
			Controller controller = getControllerByCmd(cmd); 
			if(controller != null) {
				controller.docommand(cmd); 
			}
			MysqlUtil.closeConnection();
			
			if(needToExit) {
				break;
			}
			
		}
		
		sc.close();
		
	}
	private Controller getControllerByCmd(String cmd) {
		if(cmd.startsWith("member ")) {
			return Container.memberController; 
		}
		if(cmd.startsWith("article ")) {
			return Container.articleController; 
		}
		return null;
	}

}
