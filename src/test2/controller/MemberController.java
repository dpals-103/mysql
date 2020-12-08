package test2.controller;

import java.util.Scanner;

import test2.container.Container;
import test2.dto.Member;
import test2.service.MemberService;

public class MemberController extends Controller {

	private MemberService memberService;

	public MemberController() {
		memberService = Container.memberService;
	}

	@Override
	public void docommand(String cmd) {
		if (cmd.equals("member join")) {
			doJoin(cmd);
		}
		if (cmd.equals("member login")) {
			doLogin(cmd);
		}
		if (cmd.equals("member logout")) {
			doLogout(cmd);
		}
		if (cmd.equals("member whoami")) {
			doWhoAmI(cmd);
		}
	}

	// 회원상태
	private void doWhoAmI(String cmd) {

		if (Container.session.isLogined() == false) {
			System.out.println("로그아웃 상태입니다");
			return;
		}
		if (Container.session.isLogined()) {
			
			int memberId = Container.session.isLoginedMemberId;
			Member member = memberService.getMemberByMemberId(memberId);

			System.out.printf("아이디 : %s\n", member.loginId);
			System.out.printf("회원명: %s\n", member.name);
			System.out.printf("회원번호 : %d\n", member.id);
			System.out.printf("가입날짜 : %s\n", member.regDate);
		}

	}

	// 로그아웃
	private void doLogout(String cmd) {

		if (Container.session.isLogined() == false) {
			System.out.println("이미 로그아웃되었습니다");
			return;
		}

		Container.session.logout();
		System.out.println("로그아웃 되었습니다");
	}

	// 로그인
	private void doLogin(String cmd) {
		System.out.println("==로그인==");

		if (Container.session.isLogined()) {
			System.out.println("이미 로그인되었습니다");
			return;
		}

		Scanner sc = Container.scanner;

		String loginId;
		String loginPw;

		int loginIdCount = 0;
		int loginIdMaxCount = 3;

		Member member = null;

		// 아이디 입력
		while (true) {
			if (loginIdCount >= loginIdMaxCount) {
				System.out.println("로그인에 실패하였습니다");
				return;
			}

			System.out.printf("아이디를 입력하세요: ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				loginIdCount++;
				continue;
			}

			member = memberService.getMemberById(loginId);

			if (member == null) {
				System.out.println("등록되지 않은 아이디 입니다");
				loginIdCount++;
				continue;
			}
			break;
		}

		// 비밀번호 입력

		int loginPwCount = 0;
		int loginPwMaxCount = 3;

		while (true) {
			if (loginPwCount >= loginPwMaxCount) {
				System.out.println("로그인에 실패하였습니다");
				return;
			}

			System.out.printf("비밀번호를 입력하세요: ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				loginPwCount++;
				continue;
			}

			if (member.loginPw.equals(loginPw) == false) {
				System.out.println("비밀번호가 일치하지 않습니다");
				loginPwCount++;
				continue;
			}
			break;
		}
		System.out.printf("반갑습니다 %s님. 로그인 되었습니다\n", loginId);
		Container.session.login(member.id);
	}

	private void doJoin(String cmd) {
		System.out.println("==회원가입==");

		if (Container.session.isLogined()) {
			System.out.println("이미 로그인되었습니다");
			return;
		}

		Scanner sc = Container.scanner;

		// 아이디 설정
		String loginId;
		String loginPw;
		String name;

		int loginIdCount = 0;
		int loginIdMaxCount = 3;

		while (true) {
			if (loginIdCount >= loginIdMaxCount) {
				System.out.println("회원가입에 실패하였습니다");
				return;
			}

			System.out.printf("아이디 설정 : ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				loginIdCount++;
				continue;
			} else if (memberService.isAvailableLoginId(loginId) == false) {
				System.out.println("이미 등록된 아이디입니다");
				loginIdCount++;
				continue;
			}
			break;
		}

		// 비밀번호 설정
		int loginPwCount = 0;
		int loginPwMaxCount = 3;

		while (true) {
			if (loginPwCount >= loginPwMaxCount) {
				System.out.println("회원가입에 실패하였습니다");
				return;
			}

			System.out.printf("비밀번호 설정 : ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				loginPwCount++;
				continue;
			}
			break;
		}

		// 사용자이름 등록
		int nameCount = 0;
		int nameMaxCount = 3;

		while (true) {
			if (nameCount >= nameMaxCount) {
				System.out.println("회원가입에 실패하였습니다");
				return;
			}

			System.out.printf("사용자 이름 : ");
			name = sc.nextLine().trim();

			if (name.length() == 0) {
				nameCount++;
				continue;
			}
			break;
		}
		int id = memberService.join(loginId, loginPw, name);
		System.out.printf("환영합니다. %d번 회원으로 가입되었습니다\n", id);
	}

}
