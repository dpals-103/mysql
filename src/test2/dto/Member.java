package test2.dto;

import java.util.Map;

public class Member {
	
	public String loginId;
	public String loginPw;
	public String name;
	public int id;
	public String regDate;
	

	public Member(Map<String, Object> map) {
		this.loginId = (String)map.get("loginId");
		this.loginPw= (String)map.get("loginPw");
		this.name= (String)map.get("name");
		this.regDate= (String)map.get("regDate");
		this.id= (int)map.get("id");
	}

}
