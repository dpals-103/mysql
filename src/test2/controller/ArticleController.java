package test2.controller;

import java.util.List;
import java.util.Scanner;

import test2.container.Container;
import test2.dto.Article;
import test2.service.ArticleService;
import test2.service.MemberService;

public class ArticleController extends Controller {

	private ArticleService articleService;
	private MemberService memberService;

	public ArticleController() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	@Override
	public void docommand(String cmd) {
		if (cmd.equals("article makeBoard")) {
			doMakeBoard(cmd);
		}
		if (cmd.startsWith("article selectBoard ")) {
			doSelectBoard(cmd);
		}
		if (cmd.equals("article add")) {
			doWrite(cmd);
		}
		if (cmd.startsWith("article list ")) {
			showList(cmd);
		}
		if (cmd.startsWith("article detail ")) {
			showDetail(cmd);
		}
		if (cmd.startsWith("article modify ")) {
			doModify(cmd);
		}
		if (cmd.startsWith("article delete ")) {
			doRemove(cmd);
		}
	}

	// 게시물 삭제하기
	private void doRemove(String cmd) {
		System.out.println("==게시글 삭제==");
		
		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		if (Container.session.isSelectedBoardId == 0) {
			System.out.println("게시판을 선택해주세요");
			return;
		}

		
		int inputedId = Integer.parseInt(cmd.split(" ")[2]);
		Article article = articleService.getArticle(inputedId);

		if (article == null) {
			System.out.println("존재하지 않는 게시글 입니다");
			return;
		}
		
		if (Container.session.isLoginedMemberId != article.memberId) {
			System.out.println("해당 글 작성자만 가능합니다");
			return;
		}
		
		int r = articleService.delete(inputedId);
		System.out.printf("%d번 게시물을 삭제하였습니다\n", inputedId);
	}

	// 게시물 수정하기
	private void doModify(String cmd) {
		Scanner sc = Container.scanner;
		System.out.println("==게시글 수정==");

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		if (Container.session.isSelectedBoardId == 0) {
			System.out.println("게시판을 선택해주세요");
			return;
		}

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);
		Article article = articleService.getArticle(inputedId);

		if (article == null) {
			System.out.println("존재하지 않는 게시글 입니다");
			return;
		}
		
		
		if (Container.session.isLoginedMemberId != article.memberId) {
			System.out.println("해당 글 작성자만 가능합니다");
			return;
		}

		String title;
		String body;

		System.out.printf("새 제목 : ");
		title = sc.nextLine();

		System.out.printf("새 내용 : ");
		body = sc.nextLine();

	
		articleService.modify(inputedId, title, body);

		System.out.printf("%d번 게시글이 수정되었습니다\n", inputedId);

	}

	// 게시글 상세보기
	private void showDetail(String cmd) {
		System.out.println("==게시글 상세보기==");

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		if (Container.session.isSelectedBoardId == 0) {
			System.out.println("게시판을 선택해주세요");
			return;
		}

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);
		int boardId = Container.session.isSelectedBoardId;

		articleService.hitCount(boardId, inputedId);
		Article article = articleService.getArticle(inputedId);

		if (article == null) {
			System.out.println("존재하지 않는 게시글 입니다");
			return;
		}

		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);
		System.out.printf("작성자(회원번호) : %s(%d번)\n", article.extra_writer, article.memberId);
		System.out.printf("작성시간 : %s\n", article.regDate);
		System.out.printf("수정시간 : %s\n", article.updateDate);
		System.out.printf("조회수 : %s\n", article.hit);
		System.out.printf("추천수 : %s\n", article.recommand);

	}

	// 게시판 별 리스트
	private void showList(String cmd) {
		System.out.println("==게시판리스트==");

		if (Container.session.isSelectedBoardId == 0) {
			System.out.println("게시판을 선택해주세요");
			return;
		}

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Board board = articleService.getBoardById(inputedId);

		if (board == null) {
			System.out.println("존재하지 않는 게시판 입니다");
			return;
		}

		List<Article> articles = articleService.getArticles(inputedId);

		System.out.printf("=== %s 게시판 리스트 ===\n", board.boardName);
		System.out.println("번호 / 제목 / 작성자(회원번호)/ 조회수 / 추천수 / 수정시간");

		for (Article article : articles) {
			System.out.printf("%d / %s / %s(%d번) / %d / %d / %s\n", article.id, article.title, article.extra_writer,
					article.memberId, article.hit, article.recommand, article.updateDate);
		}

	}

	// 게시글 등록
	private void doWrite(String cmd) {
		Scanner sc = Container.scanner;
		System.out.println("==게시글 등록==");

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		if (Container.session.isSelectedBoardId == 0) {
			System.out.println("게시판을 선택해주세요");
			return;
		}

		String title;
		String body;

		System.out.printf("제목 : ");
		title = sc.nextLine();

		System.out.printf("내용 : ");
		body = sc.nextLine();

		int boardId = Container.session.isSelectedBoardId;
		int memberId = Container.session.isLoginedMemberId;

		int id = articleService.add(boardId, title, body, memberId);

		System.out.printf("%d번 게시글이 생성되었습니다\n", id);

	}

	// 게시판 선택하기
	private void doSelectBoard(String cmd) {
		System.out.println("==게시판 선택==");

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		int inputedId = Integer.parseInt(cmd.split(" ")[2]);

		Board board = articleService.getBoardById(inputedId);
		if (board == null) {
			System.out.println("존재하지 않는 게시판 입니다");
			return;
		}

		System.out.printf("%s(%d)번 게시판이 선택되었습니다\n", board.boardName, inputedId);
		Container.session.selectBoard(inputedId);

	}

	// 게시판 생성하기
	private void doMakeBoard(String cmd) {
		Scanner sc = Container.scanner;
		System.out.println("==게시판 생성==");

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		String boardName;
		System.out.printf("게시판 이름 : ");
		boardName = sc.nextLine();

		Board board = articleService.getBoardByName(boardName);

		if (board != null) {
			if (board.boardName.equals(boardName)) {
				System.out.println("이미 존재하는 게시판 입니다");
				return;
			}
		}

		int id = articleService.makeBoard(boardName);
		System.out.printf("%s(%d번) 게시판이 생성되었습니다\n", boardName, id);

		Container.session.selectBoard(id);
	}

}
