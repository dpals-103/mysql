package test2.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import test2.dto.Member;
import test2.mysqlUtil.MysqlUtil;
import test2.mysqlUtil.SecSql;

public class MemberDao {

	private List<Member> member;
	private int lastMemberId;

	public MemberDao() {
		member = new ArrayList<>();
		lastMemberId = 0;
	}

	public boolean isAvailableLoginId(String loginId) {
	
		SecSql sql = new SecSql();
		sql.append("select loginId from member where loginId = ?", loginId); 
		String checkId = MysqlUtil.selectRowStringValue(sql);
		
		if(checkId.isBlank()) {
			return true;
		} if (checkId.equals(loginId)) {
			return false; 
		} return true;
	}

	
	public int join(String loginId, String loginPw, String name) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO member set loginId = ?, loginPw = ?, name =?, regDate = now()", loginId, loginPw, name);
		
		
		return MysqlUtil.insert(sql);
	}

	
	public Member getMemberById(String loginId) {
		
		SecSql sql = new SecSql();
		sql.append("select * from member where loginId = ?", loginId );
		
		Map<String, Object> map = MysqlUtil.selectRow(sql); 
		
		if(map.isEmpty()) {
			return null; 
		}
		
		return new Member(map);
	}

	public Member getMemberByMemberId(int memberId) {
		
		SecSql sql = new SecSql();
		sql.append("select * from member where id = ?", memberId);
		
		Map<String, Object> map = MysqlUtil.selectRow(sql); 
		
		if(map.isEmpty()) {
			return null; 
		}
		
		return new Member(map);
	}

}
