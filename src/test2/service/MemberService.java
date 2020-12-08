package test2.service;

import test2.container.Container;
import test2.dao.MemberDao;
import test2.dto.Member;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao;
	}

	public boolean isAvailableLoginId(String loginId) {
		return memberDao.isAvailableLoginId(loginId);
	}

	public int join(String loginId, String loginPw, String name) {
		return memberDao.join(loginId, loginPw, name);
	}

	public Member getMemberById(String loginId) {
		return memberDao.getMemberById(loginId);
	}

	public Member getMemberByMemberId(int memberId) {
		return memberDao.getMemberByMemberId(memberId);
	}

}
